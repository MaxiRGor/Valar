package harelchuk.maxim.quizwithmoxy.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.CoinValuesSingleton;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;


@InjectViewState
public class SetBankPresenter extends MvpPresenter<SetBankView> {

    private boolean is_debit;
    private long time_debit;
    private long sum_debit;
    private long[] debit_GAC;
    private long user_money;
    private long debit_time_to_increase;

    private boolean is_credit;
    private long time_credit;
    private long sum_credit;
    private long[] credit_GAC;
    private long credit_time_to_return;


    private long one_lap = 2 * 60 * 1000; //ms , == 2 min
    private long one_lap_in_seconds = 2 * 60;

    public SetBankPresenter() {

        this.is_debit = UserDataSingleton.getInstance().isIs_debit();
        this.time_debit = UserDataSingleton.getInstance().getDebit_time();
        this.sum_debit = UserDataSingleton.getInstance().getDebit_sum();
        this.user_money = UserDataSingleton.getInstance().getUser_money();

        this.is_credit = UserDataSingleton.getInstance().isIs_credit();
        this.time_credit = UserDataSingleton.getInstance().getCredit_time();
        this.sum_credit = UserDataSingleton.getInstance().getCredit_sum();

        countDebitTimeAndReward();
        countCreditTime();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);
        setBrokenButtons();
    }


    private void renewInfo() {
        this.is_debit = UserDataSingleton.getInstance().isIs_debit();
        this.time_debit = UserDataSingleton.getInstance().getDebit_time();
        this.sum_debit = UserDataSingleton.getInstance().getDebit_sum();
        this.user_money = UserDataSingleton.getInstance().getUser_money();
        this.is_credit = UserDataSingleton.getInstance().isIs_credit();
        this.time_credit = UserDataSingleton.getInstance().getCredit_time();
        this.sum_credit = UserDataSingleton.getInstance().getCredit_sum();
    }

    private void setBrokenButtons() {
        getViewState().setBrokenButtons(is_debit, is_credit);
    }

    private void countDebitTimeAndReward() {
        Date date = new Date();
        long time_now = date.getTime();
        Log.d("myLogs", "2) time_now= " + time_now);
        if (is_debit) {
            if ((time_now - time_debit) > (one_lap)) {
                Log.d("myLogs", "7) time_now-time_debit)>(one_lap) == " + time_now + "-" + time_debit + ")>(" + one_lap);
                while ((time_now - time_debit) > (one_lap)) {
                    sum_debit *= 1.1;
                    time_debit += one_lap;
                    Log.d("myLogs", "8) lap passed, sum debit=" + sum_debit);
                }

                UserDataSingleton.getInstance().setDebit_sum(sum_debit);
                UserDataSingleton.getInstance().setDebit_time(time_debit);
                Log.d("myLogs", "9) new sum_debit= " + sum_debit);
            }
        }
        debit_time_to_increase = (time_now - time_debit) / 1000; //in seconds
        Log.d("myLogs", "10) debit_time_to_increase, sec=" + debit_time_to_increase);
    }

    private void countCreditTime() {
        Date date = new Date();
        long time_now = date.getTime();
        if (is_credit) {
            if ((time_now - time_credit) > (one_lap)) {
                Log.d("myLogs", "return credit automatically");
                while ((time_now - time_credit) > (one_lap)) {
                    sum_credit *= 1.1;
                    time_credit += one_lap;
                }
                UserDataSingleton.getInstance().setCredit_sum(sum_credit);
                UserDataSingleton.getInstance().setCredit_time(time_credit);
            }

        }
        credit_time_to_return = (time_now - time_credit) / 1000; //in seconds
    }

    private void divideMoney(long debit_sum_temp, long credit_sum_temp) {
        debit_GAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(debit_sum_temp);
        credit_GAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(credit_sum_temp);
    }

    public void addDebit(long sum_to_add) {
        countDebitTimeAndReward();
        Date date = new Date();
        time_debit = date.getTime();
        Log.d("myLogs", "6) sum_debit+=sum_to_add;   " + sum_debit + sum_to_add);
        sum_debit += sum_to_add;
        user_money -= sum_to_add;

        UserDataSingleton.getInstance().setIs_debit(true);
        UserDataSingleton.getInstance().setDebit_sum(sum_debit);
        UserDataSingleton.getInstance().setDebit_time(time_debit);
        UserDataSingleton.getInstance().setUserMoney(user_money);

        renewInfo();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);

        setBrokenButtons();
    }

    public void removeDebitFromSP() {
        countDebitTimeAndReward();
        Log.d("myLogs", "11) removeDebitFromSP, userMoney = " + user_money + ", sum_debit (WAS) = " + sum_debit);
        user_money += sum_debit;
        sum_debit = 0;
        Log.d("myLogs", "12) removeDebitFromSP, userMoney = " + user_money + ", sum_debit (NOW) = " + sum_debit);

        UserDataSingleton.getInstance().setIs_debit(false);
        UserDataSingleton.getInstance().setDebit_sum(sum_debit);
        UserDataSingleton.getInstance().setUserMoney(user_money);
        UserDataSingleton.getInstance().setDebit_time(0);

        renewInfo();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);

        setBrokenButtons();
    }

    public void writeCreditIntoSP(long sum_to_get) {
        Date date = new Date();
        time_credit = date.getTime();
        sum_credit += sum_to_get;
        user_money += sum_to_get;

        UserDataSingleton.getInstance().setIs_credit(true);
        UserDataSingleton.getInstance().setCredit_sum(sum_credit);
        UserDataSingleton.getInstance().setUserMoney(user_money);
        UserDataSingleton.getInstance().setCredit_time(time_credit);
        renewInfo();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);
        getViewState().showDebit(is_debit, debit_time_to_increase, debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        setBrokenButtons();
    }

    public void returnCreditUsingSP() {
        if (user_money >= sum_credit) {
            long newMoney = user_money - sum_credit;

            UserDataSingleton.getInstance().setIs_credit(false);
            UserDataSingleton.getInstance().setCredit_sum(0);
            UserDataSingleton.getInstance().setUserMoney(newMoney);
            UserDataSingleton.getInstance().setCredit_time(0);

            renewInfo();
            countCreditTime();
            countDebitTimeAndReward();
            divideMoney(sum_debit, sum_credit);
            getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);
            getViewState().showDebit(is_debit, debit_time_to_increase, debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);

            setBrokenButtons();
        } else {
            String title = AppForContext.getContext().getResources().getString(R.string.credit);
            String text = AppForContext.getContext().getResources().getString(R.string.creditNotEnoughMoney);
            getViewState().showAlertMessage(title, text);
        }
    }

}

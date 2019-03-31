package harelchuk.maxim.quizwithmoxy.presenter;

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

    private long user_money;

    private boolean is_debit;
    private long time_debit;
    private long sum_debit;
    private long[] coinsDebitGAC;
    private int increase_debit_time_short;

    private boolean is_credit;
    private long time_credit;
    private long sum_credit;
    private long[] coinsCreditGAC;
    private int increase_credit_time_short;

    private int conversation21600000To120 = 180000;

    public SetBankPresenter() {
        renewInfo();
    }

    private void renewInfo() {
        this.user_money = UserDataSingleton.getInstance().getUser_money();
        this.is_debit = UserDataSingleton.getInstance().isIs_debit();
        this.time_debit = UserDataSingleton.getInstance().getDebit_time();
        if (is_debit)
            this.increase_debit_time_short = (int) (new Date().getTime() - this.time_debit) / conversation21600000To120;
        else this.increase_debit_time_short = 0;
        this.sum_debit = UserDataSingleton.getInstance().getDebit_sum();
        this.is_credit = UserDataSingleton.getInstance().isIs_credit();
        this.time_credit = UserDataSingleton.getInstance().getCredit_time();
        if (is_credit) this.increase_credit_time_short = (int) (new Date().getTime() - this.time_credit) / conversation21600000To120;
        else this.increase_credit_time_short = 0;
        this.sum_credit = UserDataSingleton.getInstance().getCredit_sum();
        countDebitTimeAndReward();
        countCreditTime();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, increase_debit_time_short,
                coinsDebitGAC[0], coinsDebitGAC[1], coinsDebitGAC[2], user_money);
        getViewState().showCredit(is_credit, increase_credit_time_short, coinsCreditGAC, user_money);
        setBrokenButtons();
        /*String title = AppForContext.getContext().getResources().getString(R.string.credit);
        String text = String.valueOf(increase_debit_time_short) + "   " + String.valueOf(increase_credit_time_short);
        getViewState().showAlertMessage(title, text);*/
    }


    private void countDebitTimeAndReward() {
        if (is_debit) {
            long[] updated_debit_sum_and_time = UserDataSingleton.getInstance().updated_debit_sum_and_time();
            this.sum_debit = updated_debit_sum_and_time[0];
            this.time_debit = updated_debit_sum_and_time[1];
            this.increase_debit_time_short = (int) (new Date().getTime() - this.time_debit) / conversation21600000To120;
        }
    }

    private void countCreditTime() {
        if (is_credit) {
            long[] updated_credit_sum_and_time = UserDataSingleton.getInstance().updated_credit_sum_and_time();
            this.sum_credit = updated_credit_sum_and_time[0];
            this.time_credit = updated_credit_sum_and_time[1];
            this.increase_credit_time_short = (int) (new Date().getTime() - this.time_credit) / conversation21600000To120;
        }
    }

    private void divideMoney(long debit_sum_temp, long credit_sum_temp) {
        this.coinsDebitGAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(debit_sum_temp);
        this.coinsCreditGAC = CoinValuesSingleton.getInstance().convertCoinsToGAC(credit_sum_temp);
    }

    private void setBrokenButtons() {
        getViewState().setBrokenButtons(is_debit, is_credit);
    }


    public void addDebit(long sum_to_add) {
        countDebitTimeAndReward();
        UserDataSingleton.getInstance().add_debit(sum_to_add);
        renewInfo();
    }

    public void returnDebit() {
        if (is_debit) {
            countDebitTimeAndReward();
            UserDataSingleton.getInstance().remove_debit();
            renewInfo();
        }
    }

    public void getCredit(long sum_to_get) {
        if (!is_credit) {
            UserDataSingleton.getInstance().getCredit(sum_to_get);
            renewInfo();
        }
    }

    public void returnCredit() {
        if (this.user_money >= this.sum_credit) {
            UserDataSingleton.getInstance().removeCredit();
            renewInfo();
        } else {
            String title = AppForContext.getContext().getResources().getString(R.string.credit);
            String text = AppForContext.getContext().getResources().getString(R.string.creditNotEnoughMoney);
            getViewState().showAlertMessage(title, text);
        }
    }

}

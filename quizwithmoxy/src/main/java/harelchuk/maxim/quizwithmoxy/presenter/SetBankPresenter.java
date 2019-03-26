package harelchuk.maxim.quizwithmoxy.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.SharedPreferencesFunctions;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.CREDIT_SUM;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.CREDIT_TIME;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_SUM;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_TIME;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_CREDIT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_DEBIT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.MONEY_TEMP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_USER;

@InjectViewState
public class SetBankPresenter extends MvpPresenter<SetBankView> {

    private SharedPreferences sharedPreferencesUser;
    private SharedPreferencesFunctions sharedPreferencesFunctions;
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
    private long one_lap_in_seconds = 2*60;

    public SetBankPresenter() {
        sharedPreferencesUser = AppForContext.getContext().
                getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferencesFunctions = new SharedPreferencesFunctions();
        getInfoFromSP();
        countDebitTimeAndReward();
        countCreditTime();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit, one_lap_in_seconds, credit_time_to_return, credit_GAC, user_money);
        setBrokenButtons();
    }




    private void getInfoFromSP() {
        is_debit = sharedPreferencesUser.getBoolean(IS_DEBIT, false);
        time_debit = sharedPreferencesUser.getLong(DEBIT_TIME, 0);
        sum_debit = sharedPreferencesUser.getLong(DEBIT_SUM, 0);
        user_money = sharedPreferencesUser.getLong(MONEY_TEMP, 0);

        is_credit = sharedPreferencesUser.getBoolean(IS_CREDIT, false);
        time_credit = sharedPreferencesUser.getLong(CREDIT_TIME, 0);
        sum_credit = sharedPreferencesUser.getLong(CREDIT_SUM, 0);

        Log.d("myLogs", "1) is_debit= " + is_debit + " sum_debit= " + sum_debit + " user_money= " + user_money);
    }

    private void setBrokenButtons() {
        getViewState().setBrokenButtons(is_debit,is_credit);
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
                SharedPreferences.Editor editor = sharedPreferencesUser.edit();
                editor.putLong(DEBIT_SUM, sum_debit);
                editor.putLong(DEBIT_TIME, time_debit);
                editor.apply();
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
                SharedPreferences.Editor editor = sharedPreferencesUser.edit();
                editor.putLong(CREDIT_SUM, sum_credit);
                editor.putLong(CREDIT_TIME, time_credit);
                editor.apply();
            }

        }
        credit_time_to_return = (time_now - time_credit) / 1000; //in seconds
    }

    private void divideMoney(long debit_sum_temp, long credit_sum_temp) {
        debit_GAC = sharedPreferencesFunctions.coins_GD_AD_CP(debit_sum_temp);
        credit_GAC = sharedPreferencesFunctions.coins_GD_AD_CP(credit_sum_temp);
    }

    public void writeDebitIntoSP(long sum_to_add) {
        countDebitTimeAndReward();
        Date date = new Date();
        time_debit = date.getTime();
        Log.d("myLogs", "6) sum_debit+=sum_to_add;   " + sum_debit + sum_to_add);
        sum_debit += sum_to_add;
        user_money -= sum_to_add;
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putBoolean(IS_DEBIT, true);
        editor.putLong(DEBIT_SUM, sum_debit);
        editor.putLong(MONEY_TEMP, user_money);
        editor.putLong(DEBIT_TIME, time_debit);
        editor.commit();
        getInfoFromSP();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit,one_lap_in_seconds,credit_time_to_return,credit_GAC,user_money);

        setBrokenButtons();
    }

    public void removeDebitFromSP() {
        countDebitTimeAndReward();
        Log.d("myLogs", "11) removeDebitFromSP, userMoney = " + user_money + ", sum_debit (WAS) = " + sum_debit);
        user_money += sum_debit;
        sum_debit = 0;
        Log.d("myLogs", "12) removeDebitFromSP, userMoney = " + user_money + ", sum_debit (NOW) = " + sum_debit);
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putBoolean(IS_DEBIT, false);
        editor.putLong(DEBIT_SUM, sum_debit);
        editor.putLong(MONEY_TEMP, user_money);
        editor.putLong(DEBIT_TIME, 0);
        editor.commit();
        getInfoFromSP();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showDebit(is_debit, debit_time_to_increase,
                debit_GAC[0], debit_GAC[1], debit_GAC[2], user_money);
        getViewState().showCredit(is_credit,one_lap_in_seconds,credit_time_to_return,credit_GAC,user_money);

        setBrokenButtons();
    }

    public void writeCreditIntoSP(long sum_to_get) {
        Date date = new Date();
        time_credit = date.getTime();
        //Log.d("myLogs", "6) sum_debit+=sum_to_add;   " + sum_debit + sum_to_add);
        sum_credit += sum_to_get;
        user_money += sum_to_get;
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putBoolean(IS_CREDIT, true);
        editor.putLong(CREDIT_SUM, sum_credit);
        editor.putLong(MONEY_TEMP, user_money);
        editor.putLong(CREDIT_TIME, time_credit);
        editor.commit();
        getInfoFromSP();

        //countDebitTimeAndReward();
        countCreditTime();
        countDebitTimeAndReward();
        divideMoney(sum_debit, sum_credit);
        getViewState().showCredit(is_credit,one_lap_in_seconds,credit_time_to_return,credit_GAC,user_money);
        getViewState().showDebit(is_debit,debit_time_to_increase,debit_GAC[0],debit_GAC[1],debit_GAC[2],user_money);

        setBrokenButtons();

    }

    public void returnCreditUsingSP() {
        //user_money = sharedPreferencesUser.getLong(MONEY_TEMP,0);
        if(user_money>=sum_credit){
            long newMoney = user_money-sum_credit;
            SharedPreferences.Editor editor = sharedPreferencesUser.edit();
            editor.putBoolean(IS_CREDIT, false);
            editor.putLong(CREDIT_SUM, 0);
            editor.putLong(MONEY_TEMP, newMoney);
            editor.putLong(CREDIT_TIME, 0);
            editor.commit();
            getInfoFromSP();

            countCreditTime();
            countDebitTimeAndReward();
            divideMoney(sum_debit,sum_credit);
            getViewState().showCredit(is_credit,one_lap_in_seconds,credit_time_to_return,credit_GAC,user_money);
            getViewState().showDebit(is_debit,debit_time_to_increase,debit_GAC[0],debit_GAC[1],debit_GAC[2],user_money);

            setBrokenButtons();
        } else {
            String title = AppForContext.getContext().getResources().getString(R.string.credit);
            String text = AppForContext.getContext().getResources().getString(R.string.creditNotEnoughMoney);
            getViewState().showAlertMessage(title,text);
        }
    }

}

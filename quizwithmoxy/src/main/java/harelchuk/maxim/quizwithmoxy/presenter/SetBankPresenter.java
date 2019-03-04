package harelchuk.maxim.quizwithmoxy.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.view.SetBankView;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_SUM;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_TIME;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_DEBIT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.MONEY_TEMP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_USER;

@InjectViewState
public class SetBankPresenter extends MvpPresenter<SetBankView> {

    SharedPreferences sharedPreferencesUser;
    private boolean is_debit;
    private long time_debit;
    private int sum_debit;
    private int debit_GD;
    private int debit_AD;
    private int debit_CP;
    private int user_money;
    private long time_now;
    private int time_to_increase;

    private int one_lap = 2*60*1000; //ms , == 2 min

    public SetBankPresenter(){
        sharedPreferencesUser = AppForContext.getContext().
                getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);
        getInfoFromSP();
        countTimeAndReward();
        divideMoney(sum_debit);
        getViewState().showDebit(is_debit,time_to_increase,
                debit_GD,debit_AD,debit_CP,user_money);
    }

    private void getInfoFromSP(){
        is_debit=sharedPreferencesUser.getBoolean(IS_DEBIT,false);
        time_debit=sharedPreferencesUser.getLong(DEBIT_TIME,0);
        sum_debit=sharedPreferencesUser.getInt(DEBIT_SUM,0);
        user_money =sharedPreferencesUser.getInt(MONEY_TEMP,0);
        Log.d("myLogs", "1) is_debit= "+ is_debit + " sum_debit= "+ sum_debit+" user_money= " + user_money);
    }

    private void countTimeAndReward() {
        Date date = new Date();
        time_now= date.getTime();
        Log.d("myLogs", "2) time_now= "+ time_now);
        if(is_debit){
            if((time_now-time_debit)>(one_lap)){
                Log.d("myLogs", "7) time_now-time_debit)>(one_lap) == "+ time_now +"-" + time_debit +")>("+one_lap);
                while((time_now-time_debit)>(one_lap)){
                    sum_debit*=1.1;
                    time_debit+=one_lap;
                    Log.d("myLogs","8) lap passed, sum debit="+sum_debit);
                }
                SharedPreferences.Editor editor = sharedPreferencesUser.edit();
                editor.putInt(DEBIT_SUM,sum_debit);
                editor.putLong(DEBIT_TIME,time_debit);
                editor.commit();
                Log.d("myLogs","9) new sum_debit= "+sum_debit);
            }
            time_to_increase=(int) (time_now-time_debit)/1000; //in seconds
            Log.d("myLogs","10) time_to_increase, sec="+time_to_increase);
        }
    }

    private void divideMoney(int sum) {
        debit_GD=sum/(11760);
        sum-=debit_GD*11760;
        debit_AD=sum/(56);
        sum-=debit_AD*56;
        debit_CP=sum;
        Log.d("myLogs","3) divideMoney="+debit_GD+" GD "+debit_AD+" AD "+debit_CP+" CP "+sum+"(zero) money");
    }

    public void writeDebitIntoSP(int sum_to_add) {
        countTimeAndReward();
        Date date = new Date();
        time_debit=date.getTime();
        Log.d("myLogs","6) sum_debit+=sum_to_add;   "+sum_debit+sum_to_add);
        sum_debit+=sum_to_add;
        user_money-=sum_to_add;
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putBoolean(IS_DEBIT,true);
        editor.putInt(DEBIT_SUM,sum_debit);
        editor.putInt(MONEY_TEMP,user_money);
        editor.putLong(DEBIT_TIME,time_debit);
        editor.commit();
        getInfoFromSP();
        countTimeAndReward();
        divideMoney(sum_debit);
        getViewState().showDebit(is_debit,time_to_increase,
                debit_GD,debit_AD,debit_CP,user_money);
    }

    public void removeDebitFromSP() {
        countTimeAndReward();
        Log.d("myLogs","11) removeDebitFromSP, userMoney = "+user_money + ", sum_debit (WAS) = " + sum_debit);
        user_money+=sum_debit;
        sum_debit=0;
        Log.d("myLogs","12) removeDebitFromSP, userMoney = "+user_money + ", sum_debit (NOW) = " + sum_debit);
        SharedPreferences.Editor editor= sharedPreferencesUser.edit();
        editor.putBoolean(IS_DEBIT,false);
        editor.putInt(DEBIT_SUM,sum_debit);
        editor.putInt(MONEY_TEMP,user_money);
        editor.putLong(DEBIT_TIME,0);
        editor.commit();
        getInfoFromSP();
        countTimeAndReward();
        divideMoney(sum_debit);
        getViewState().showDebit(is_debit,time_to_increase,
                debit_GD,debit_AD,debit_CP,user_money);
    }
}

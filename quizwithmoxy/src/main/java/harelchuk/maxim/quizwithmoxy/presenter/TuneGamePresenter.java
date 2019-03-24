package harelchuk.maxim.quizwithmoxy.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.SharedPreferencesFunctions;
import harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.*;

@InjectViewState
public class TuneGamePresenter extends MvpPresenter<TuneGameView> {

    private SharedPreferences sharedPreferencesMoney;
    private SharedPreferences sharedPreferencesUser;
    private SharedPreferencesFunctions sharedPreferencesFunctions;
    private int[] levels;
    private int[] costs;
    private int[] rewards;

    public TuneGamePresenter() {
        Log.d("myLogs", "TuneGamePresenter const");
        sharedPreferencesFunctions = new SharedPreferencesFunctions();
        sharedPreferencesMoney = AppForContext.getContext().
                getSharedPreferences(SharedPreferencesInitializer.SHARED_PREFERENCES_MONEY, Context.MODE_PRIVATE);
        sharedPreferencesUser = AppForContext.getContext().
                getSharedPreferences(SharedPreferencesInitializer.SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);

        levels = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        costs = new int[levels.length];
        rewards = new int[levels.length];



        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> fillList = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int defValue = 0;
                costs[0] = sharedPreferencesMoney.getInt(L_1_COST_CP, defValue);
                costs[1] = sharedPreferencesMoney.getInt(L_2_COST_CP, defValue);
                costs[2] = sharedPreferencesMoney.getInt(L_3_COST_CP, defValue);
                costs[3] = sharedPreferencesMoney.getInt(L_4_COST_AD, defValue);
                costs[4] = sharedPreferencesMoney.getInt(L_5_COST_AD, defValue);
                costs[5] = sharedPreferencesMoney.getInt(L_6_COST_AD, defValue);
                costs[6] = sharedPreferencesMoney.getInt(L_7_COST_GD, defValue);
                costs[7] = sharedPreferencesMoney.getInt(L_8_COST_GD, defValue);
                costs[8] = sharedPreferencesMoney.getInt(L_9_COST_GD, defValue);
                costs[9] = sharedPreferencesMoney.getInt(L_10_COST_GD, defValue);
                rewards[0] = sharedPreferencesMoney.getInt(L_1_REWARD_CP, defValue);
                rewards[1] = sharedPreferencesMoney.getInt(L_2_REWARD_CP, defValue);
                rewards[2] = sharedPreferencesMoney.getInt(L_3_REWARD_CP, defValue);
                rewards[3] = sharedPreferencesMoney.getInt(L_4_REWARD_AD, defValue);
                rewards[4] = sharedPreferencesMoney.getInt(L_5_REWARD_AD, defValue);
                rewards[5] = sharedPreferencesMoney.getInt(L_6_REWARD_AD, defValue);
                rewards[6] = sharedPreferencesMoney.getInt(L_7_REWARD_GD, defValue);
                rewards[7] = sharedPreferencesMoney.getInt(L_8_REWARD_GD, defValue);
                rewards[8] = sharedPreferencesMoney.getInt(L_9_REWARD_GD, defValue);
                rewards[9] = sharedPreferencesMoney.getInt(L_10_REWARD_GD, defValue);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getViewState().fillLevelList(levels, costs, rewards);
            }
        };
        fillList.execute();
    }

    public void writeOff(long money_to_write_off) {
        long money = sharedPreferencesUser.getLong(MONEY_TEMP,0);
        money-=money_to_write_off;
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putLong(MONEY_TEMP,money);
        editor.apply();
    }

    public void showUsersMoneyAndBF() {
        long money = sharedPreferencesUser.getLong(MONEY_TEMP,0);
        boolean isBooks = sharedPreferencesUser.getBoolean(IS_BOOKS,false);
        boolean isSeries = sharedPreferencesUser.getBoolean(IS_FILMS,false);
        long[] money_GD_AD_CP = sharedPreferencesFunctions.coins_GD_AD_CP(money);
        getViewState().fillCoins(money_GD_AD_CP, isBooks, isSeries);
    }
}







/*
    @SuppressLint("StaticFieldLeak")
    public AsyncTask<Void, Void, Void> getNumberOfQuestionForLevel = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                database = databaseHelper.open();
                cursor = database.query(DatabaseHelper.QTABLE, new String[]{DatabaseHelper.COLUMN_Q_LEXEL},
                        null, null, null, null, null, null);

                if (cursor != null) {
                    int i;
                    if (cursor.moveToFirst()) do {
                        i = cursor.getInt(0); //get level value
                        if (!levels.contains(i)) {
                            levels.add(i);
                            numberOfQuestions.add(0);
                        }
                        numberOfQuestions.set(i - 1, numberOfQuestions.get(i - 1) + 1); //add 1 to number of question by level
                    } while (cursor.moveToNext());
                    cursor.close();
                }

                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("myLogs", "SQLException ");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            fillList();
        }
    };

    private void fillList() {

        int[] lvls = new int[levels.size()];
        int[] qstns = new int[levels.size()];
        for (int i = 0; i < levels.size(); i++) {
            lvls[i] = levels.get(i);
            qstns[i] = numberOfQuestions.get(i);
        }
        getViewState().fillLevelList(lvls, qstns);
    }

    */

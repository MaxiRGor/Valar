package harelchuk.maxim.quizwithmoxy.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import harelchuk.maxim.quizwithmoxy.model.CoinConversation;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

@InjectViewState
public class TuneGamePresenter extends MvpPresenter<TuneGameView> {


    public TuneGamePresenter() {
        Log.d("myLogs", "TuneGamePresenter const");
        final int[] levels = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final int[] rewards = new int[10];
        final int[] costs = new int[10];

        ArrayList<Map<String, Integer>> data = new ArrayList<>(10);

        costs[0] = UserDataSingleton.getInstance().getL_1_cost_cp();
        costs[1] = UserDataSingleton.getInstance().getL_2_cost_cp();
        costs[2] = UserDataSingleton.getInstance().getL_3_cost_cp();
        costs[3] = UserDataSingleton.getInstance().getL_4_cost_ad();
        costs[4] = UserDataSingleton.getInstance().getL_5_cost_ad();
        costs[5] = UserDataSingleton.getInstance().getL_6_cost_ad();
        costs[6] = UserDataSingleton.getInstance().getL_7_cost_gd();
        costs[7] = UserDataSingleton.getInstance().getL_8_cost_gd();
        costs[8] = UserDataSingleton.getInstance().getL_9_cost_gd();
        costs[9] = UserDataSingleton.getInstance().getL_10_cost_gd();
        rewards[0] = UserDataSingleton.getInstance().getL_1_reward_cp();
        rewards[1] = UserDataSingleton.getInstance().getL_2_reward_cp();
        rewards[2] = UserDataSingleton.getInstance().getL_3_reward_cp();
        rewards[3] = UserDataSingleton.getInstance().getL_4_reward_ad();
        rewards[4] = UserDataSingleton.getInstance().getL_5_reward_ad();
        rewards[5] = UserDataSingleton.getInstance().getL_6_reward_ad();
        rewards[6] = UserDataSingleton.getInstance().getL_7_reward_gd();
        rewards[7] = UserDataSingleton.getInstance().getL_8_reward_gd();
        rewards[8] = UserDataSingleton.getInstance().getL_9_reward_gd();
        rewards[9] = UserDataSingleton.getInstance().getL_10_reward_gd();

        Map<String, Integer> map;

        for (int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put("level", levels[i]);
            map.put("cost", costs[i]);
            map.put("reward", rewards[i]);
            if (levels[i] == 1 || levels[i] == 2 || levels[i] == 3) {
                map.put("coin", 2);
            }
            if (levels[i] == 4 || levels[i] == 5 || levels[i] == 6) {
                map.put("coin", 1);
            }
            if (levels[i] == 7 || levels[i] == 8 || levels[i] == 9 || levels[i] == 10) {
                map.put("coin", 0);
            }
            data.add(map);
        }



        getViewState().fillLevelList(data, costs);
    }


    public void writeOff(long money_to_write_off) {
        long money = UserDataSingleton.getInstance().getUser_money();
        money -= money_to_write_off;
        UserDataSingleton.getInstance().setUserMoney(money);
    }

    public void showUsersMoneyAndBF() {
        long money = UserDataSingleton.getInstance().getUser_money();
        long[] money_GD_AD_CP = CoinConversation.coins_GD_AD_CP(money);
        getViewState().fillCoins(money_GD_AD_CP);
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

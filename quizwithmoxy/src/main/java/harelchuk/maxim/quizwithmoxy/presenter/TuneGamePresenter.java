package harelchuk.maxim.quizwithmoxy.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.CoinValuesSingleton;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

@InjectViewState
public class TuneGamePresenter extends MvpPresenter<TuneGameView> {


    public TuneGamePresenter() {
        Log.d("myLogs", "TuneGamePresenter const");
        int[] levels = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] rewards = CoinValuesSingleton.getInstance().getRewardCoins();
        int[] costs = CoinValuesSingleton.getInstance().getCostCoins();
        int[] coinImagesInt = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 1 || i == 2) {
                coinImagesInt[i] = R.drawable.ic_money_warior;
            }
            if (i == 3 || i == 4 || i == 5) {
                coinImagesInt[i] = R.drawable.ic_money_deer;
            }
            if (i == 6 || i == 7 || i == 8 || i == 9) {
                coinImagesInt[i] = R.drawable.ic_money_dragon;
            }
        }
        getViewState().fillLevelList(levels, rewards, costs, coinImagesInt);
    }


    public void subtractMoney(long subtract) {
        UserDataSingleton.getInstance().subtractUserMoney(subtract);
    }

    public void showUsersMoneyAndBF() {
        long money = UserDataSingleton.getInstance().getUser_money();
        long[] money_GD_AD_CP = CoinValuesSingleton.getInstance().convertCoinsToGAC(money);
        getViewState().fillCoins(money_GD_AD_CP);
    }
}


//ArrayList<Map<String, Integer>> data = new ArrayList<>(10);


//Map<String, Integer> map;

   /*     for (int i = 0; i < 10; i++) {
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
        }*/



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

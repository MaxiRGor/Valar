package harelchuk.maxim.quizwithmoxy.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import harelchuk.maxim.quizwithmoxy.model.CoinConversation;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.StatisticsView;


@InjectViewState
public class StatisticsPresenter extends MvpPresenter<StatisticsView> {


    private String user_name;
    private long user_money;
    private long money_GD;
    private long money_AD;
    private long money_CP;

    private int number_easy_games;
    private int number_medium_games;
    private int number_hard_games;

    private int number_easy_winnings;
    private int number_medium_winnings;
    private int number_hard_winnings;

    private boolean is_books;
    private boolean is_films;

    private int current_theme;
    private boolean is_skin_targar;
    private boolean is_skin_stark;
    private boolean is_skin_lann;
    private boolean is_skin_night;

    private boolean is_credit;
    private long credit_time;
    private long credit_sum;

    private boolean is_debit;
    private long debit_time;
    private long debit_sum;


    public StatisticsPresenter() {

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> getUsersStatistics = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                user_name = UserDataSingleton.getInstance().getUser_name();
                user_money = UserDataSingleton.getInstance().getUser_money();


                long[] mt = CoinConversation.coins_GD_AD_CP(user_money);
                money_GD = mt[0];
                money_AD = mt[1];
                money_CP = mt[2];

                number_easy_games = UserDataSingleton.getInstance().getNumber_easy_games();
                number_medium_games = UserDataSingleton.getInstance().getNumber_medium_games();
                number_hard_games = UserDataSingleton.getInstance().getNumber_hard_games();

                number_easy_winnings = UserDataSingleton.getInstance().getNumber_easy_winnings();
                number_medium_winnings = UserDataSingleton.getInstance().getNumber_medium_winnings();
                number_hard_winnings = UserDataSingleton.getInstance().getNumber_hard_winnings();

                is_books = UserDataSingleton.getInstance().isIs_books();
                is_films = UserDataSingleton.getInstance().isIs_films();

                current_theme = UserDataSingleton.getInstance().getCurrent_theme();
                is_skin_targar = UserDataSingleton.getInstance().isIs_skin_targar();
                is_skin_stark = UserDataSingleton.getInstance().isIs_skin_stark();
                is_skin_lann = UserDataSingleton.getInstance().isIs_skin_lann();
                is_skin_night = UserDataSingleton.getInstance().isIs_skin_night();

                is_credit = UserDataSingleton.getInstance().isIs_credit();
                credit_time = UserDataSingleton.getInstance().getCredit_time();
                credit_sum = UserDataSingleton.getInstance().getCredit_sum();

                is_debit = UserDataSingleton.getInstance().isIs_debit();
                debit_time = UserDataSingleton.getInstance().getDebit_time();
                debit_sum = UserDataSingleton.getInstance().getDebit_sum();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getViewState().showStatistics(user_name, money_GD, money_AD, money_CP,
                        number_easy_games, number_medium_games, number_hard_games,
                        number_easy_winnings, number_medium_winnings, number_hard_winnings,
                        is_books, is_films,
                        current_theme, is_skin_targar, is_skin_stark, is_skin_lann, is_skin_night,
                        is_credit, credit_time, credit_sum,
                        is_debit, debit_time, debit_sum);
            }
        };
        Log.d("myLogs", "Statistics Presenter started");
        getUsersStatistics.execute();
    }

    public void changeName(String newName) {
        UserDataSingleton.getInstance().setUser_name(newName);
    }
}


/*
    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, Void> getUsersStatistics = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            databaseHelper = new DatabaseHelper(AppForContext.getContext());
            try {
                database = databaseHelper.open();
                cursor = database.query(DatabaseHelper.UTABLE, null, null, null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) do {
                        Log.d("myLogs", "User info: ");
                        user_name = cursor.getString(0);
                        level = cursor.getInt(1);
                        score = cursor.getInt(2);
                        number_of_answers = cursor.getInt(3);
                        number_of_games = cursor.getInt(4);
                        percent_of_right = cursor.getInt(5);
                    } while (cursor.moveToNext());
                    cursor.close();
                }
                database.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            databaseHelper.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getViewState().showStatistics(user_name, level, score, number_of_answers,
                    number_of_games, percent_of_right);
        }
    };
*/

package harelchuk.maxim.quizwithmoxy.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.sql.SQLException;

import harelchuk.maxim.quizwithmoxy.R;
import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.DatabaseHelper;
import harelchuk.maxim.quizwithmoxy.view.StatisticsView;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.CREDIT_SUM;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.CREDIT_TIME;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_SUM;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.DEBIT_TIME;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_BOOKS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_CREDIT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_DEBIT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_FILMS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_SKIN_LANN;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_SKIN_NIGHT;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_SKIN_STARK;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.IS_SKIN_TARGAR;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.MONEY_ALL;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.MONEY_TEMP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_EASY_GAMES;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_EASY_WINNINGS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_HARD_GAMES;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_HARD_WINNINGS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_MEDIUM_GAMES;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.NUMBER_MEDIUM_WINNINGS;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_USER;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.USER_NAME;


@InjectViewState
public class StatisticsPresenter extends MvpPresenter<StatisticsView> {

    private SharedPreferences sharedPreferencesUser;

    private String user_name;
    private int money_temp;
    private int money_GD_temp;
    private int money_AD_temp;
    private int money_CP_temp;

    private int money_all;
    private int money_GD_all;
    private int money_AD_all;
    private int money_CP_all;

    private int number_easy_games;
    private int number_medium_games;
    private int number_hard_games;

    private int number_easy_winnings;
    private int number_medium_winnings;
    private int number_hard_winnings;

    private boolean is_books;
    private boolean is_films;

    private boolean is_skin_targar;
    private boolean is_skin_stark;
    private boolean is_skin_lann;
    private boolean is_skin_night;

    private boolean is_credit;
    private long credit_time;
    private int credit_sum;

    private boolean is_debit;
    private long debit_time;
    private int debit_sum;


    public StatisticsPresenter() {
        sharedPreferencesUser = AppForContext.getContext().getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> getUsersStatistics = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int defValue = Integer.valueOf(R.string.zero_number);

                user_name = sharedPreferencesUser.getString(USER_NAME, String.valueOf(R.string.user_name));
                money_temp=sharedPreferencesUser.getInt(MONEY_TEMP,defValue);
                money_all= sharedPreferencesUser.getInt(MONEY_ALL,defValue);

                divideMoneyTemp(money_temp);
                divideMoneyAll(money_all);

                number_easy_games= sharedPreferencesUser.getInt(NUMBER_EASY_GAMES,defValue);
                number_medium_games= sharedPreferencesUser.getInt(NUMBER_MEDIUM_GAMES,defValue);
                number_hard_games= sharedPreferencesUser.getInt(NUMBER_HARD_GAMES,defValue);

                number_easy_winnings=sharedPreferencesUser.getInt(NUMBER_EASY_WINNINGS,defValue);
                number_medium_winnings= sharedPreferencesUser.getInt(NUMBER_MEDIUM_WINNINGS,defValue);
                number_hard_winnings= sharedPreferencesUser.getInt(NUMBER_HARD_WINNINGS,defValue);

                is_books=sharedPreferencesUser.getBoolean(IS_BOOKS,false);
                is_films= sharedPreferencesUser.getBoolean(IS_FILMS,true);

                is_skin_targar= sharedPreferencesUser.getBoolean(IS_SKIN_TARGAR,true);
                is_skin_stark = sharedPreferencesUser.getBoolean(IS_SKIN_STARK,false);
                is_skin_lann= sharedPreferencesUser.getBoolean(IS_SKIN_LANN,false);
                is_skin_night=sharedPreferencesUser.getBoolean(IS_SKIN_NIGHT,false);

                is_credit= sharedPreferencesUser.getBoolean(IS_CREDIT,false);
                credit_time=sharedPreferencesUser.getLong(CREDIT_TIME,defValue);
                credit_sum=sharedPreferencesUser.getInt(CREDIT_SUM,defValue);

                is_debit= sharedPreferencesUser.getBoolean(IS_DEBIT,false);
                debit_time= sharedPreferencesUser.getLong(DEBIT_TIME,defValue);
                debit_sum=sharedPreferencesUser.getInt(DEBIT_SUM,defValue);
                return null;
            }

            private void divideMoneyAll(int money_all_divide) {
                money_GD_all=money_all_divide/(11760);
                money_all_divide-=money_GD_all*11760;
                money_AD_all=money_all_divide/(56);
                money_all_divide-=money_AD_all*56;
                money_CP_all=money_all_divide;
            }

            private void divideMoneyTemp(int money_temp_divide) {
                money_GD_temp=money_temp_divide/(11760);
                money_temp_divide-=money_GD_temp*11760;
                money_AD_temp=money_temp_divide/(56);
                money_temp_divide-=money_AD_temp*56;
                money_CP_temp=money_temp_divide;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getViewState().showStatistics(user_name,money_GD_temp,money_AD_temp,money_CP_temp,
                        money_GD_all,money_AD_all,money_CP_all,
                        number_easy_games,number_medium_games,number_hard_games,
                        number_easy_winnings,number_medium_winnings,number_hard_winnings,
                        is_books,is_films,
                        is_skin_targar,is_skin_stark,is_skin_lann,is_skin_night,
                        is_credit,credit_time,credit_sum,
                        is_debit,debit_time,debit_sum);
            }
        };
        Log.d("myLogs", "Statistics Presenter started");
        getUsersStatistics.execute();
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

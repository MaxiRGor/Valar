package harelchuk.maxim.quizwithmoxy;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.sql.SQLException;


@InjectViewState
public class StatisticsPresenter extends MvpPresenter<StatisticsView> {

    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    Cursor cursor;

    String user_name;
    int level;
    int score;
    int number_of_answers;
    int number_of_games;
    int percent_of_right;

    StatisticsPresenter() {
        getUsersStatistics.execute();
        Log.d("myLogs", "Statistics Presenter started");
    }

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

}

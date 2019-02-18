package harelchuk.maxim.quizwithmoxy.presenter;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.sql.SQLException;
import java.util.ArrayList;

import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.DatabaseHelper;
import harelchuk.maxim.quizwithmoxy.view.TuneGameView;

@InjectViewState
public class TuneGamePresenter extends MvpPresenter<TuneGameView> {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private Cursor cursor;

    private ArrayList<Integer> levels;
    private ArrayList<Integer> numberOfQuestions;


    public TuneGamePresenter() {
        this.databaseHelper = new DatabaseHelper(AppForContext.getContext());
        Log.d("myLogs", "TuneGamePresenter const");
        levels = new ArrayList<>();
        numberOfQuestions = new ArrayList<>();
        getNumberOfQuestionForLevel.execute();
    }

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
        getViewState().chooseLevel(lvls, qstns);
    }
}

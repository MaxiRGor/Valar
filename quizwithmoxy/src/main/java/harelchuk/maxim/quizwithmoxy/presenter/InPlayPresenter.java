package harelchuk.maxim.quizwithmoxy.presenter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.sql.SQLException;
import java.util.List;

import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.DatabaseHelper;
import harelchuk.maxim.quizwithmoxy.model.NetworkService;
import harelchuk.maxim.quizwithmoxy.model.Question;
import harelchuk.maxim.quizwithmoxy.view.InPlayView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class InPlayPresenter extends MvpPresenter<InPlayView> {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private SharedPreferences sharedPreferences;


    private Cursor cursor;

    private Context context;

    private int level;
    private int questionsToEnd;

    private int question_cursor;
    private int addScore;

    private boolean is_lose;


    private List<Question> questions;


    public InPlayPresenter() {
        this.context = getContext();
        getLevel();
        this.questionsToEnd = 10;
        this.question_cursor = 0;
        this.is_lose = false;
        Log.d("myLogs", "InPlayPresenter const");
        getViewState().findElements();
    }


    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, Void> sendInfoToUserStat = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            databaseHelper = new DatabaseHelper(context);
            ContentValues contentValues = new ContentValues();
            String[] columns = {DatabaseHelper.COLUMN_U_LEVEL, DatabaseHelper.COLUMN_U_SCORE,
                    DatabaseHelper.COLUMN_U_NUMBER_OF_ANSWERS, DatabaseHelper.COLUMN_U_NUMBER_OF_GAMES};        // 4 columns

            try {
                database = databaseHelper.open();
                cursor = database.query(DatabaseHelper.UTABLE, columns, null, null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) do {
                        Log.d("myLogs", "Getting statistics ");
                        int lvl = cursor.getInt(0);
                        int scr = cursor.getInt(1);
                        int noa = cursor.getInt(2);
                        int nog = cursor.getInt(3);
                        contentValues = changeContentValues(lvl, scr, noa, nog);
                    } while (cursor.moveToNext());
                    cursor.close();
                }

                int update = database.update(DatabaseHelper.UTABLE, contentValues, null, null);
                Log.d("MyLogs", "updated rows count = " + update);


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("MyLogs", "Statistics send");
            getViewState().showAddedScore(addScore);
        }
    };

    private ContentValues changeContentValues(int lvl, int scr, int noa, int nog) {

        int add_noa;
        int add_nog;

        if (is_lose) addScore = level * question_cursor;
        else addScore = level * question_cursor * 10;

        scr += addScore;

        add_noa = question_cursor;
        noa += add_noa;
        add_nog = 1;
        nog += add_nog;

        switch (lvl) {
            case 1:
                if (scr >= 100) lvl++;
                break;
            case 2:
                if (scr >= 300) lvl++;
                break;
            case 3:
                if (scr >= 600) lvl++;
                break;
            case 4:
                if (scr >= 1000) lvl++;
                break;
            case 5:
                break;
        }

        Log.d("MyLogs", "score added " + addScore);
        Log.d("MyLogs", "level " + lvl);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_U_LEVEL, lvl);
        contentValues.put(DatabaseHelper.COLUMN_U_SCORE, scr);
        contentValues.put(DatabaseHelper.COLUMN_U_NUMBER_OF_ANSWERS, noa);
        contentValues.put(DatabaseHelper.COLUMN_U_NUMBER_OF_GAMES, nog);
        return contentValues;
    }

    private void sendQuestionToTheView() {

        getViewState().showQuestion(questionsToEnd, questions.get(question_cursor).getQuestion_text(),
                questions.get(question_cursor).getAnswer_one(), questions.get(question_cursor).getAnswer_two(),
                questions.get(question_cursor).getAnswer_three(), questions.get(question_cursor).getAnswer_four(),
                questions.get(question_cursor).getCategory());

        //Log.d("myLogs", "Right answer" + questions.get(question_cursor).getRight_answer());

    }


    private void getLevel() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.level = sharedPreferences.getInt("level", 0) + 1;
        Log.d("myLogs", "Level in presenter = " + level);
        getQuestionsByLevel(level);
    }

    private void getQuestionsByLevel(int lvl) {
        NetworkService.getInstance().getJSONApi().getByLevel(lvl).enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                questions = response.body();
                Log.d("myLogs", "Connected");
                if (questions != null) {
                    sendQuestionToTheView();
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("myLogs", "Not Connected");
            }
        });
    }

    private Context getContext() {
        return AppForContext.getContext();
    }

    public void checkAnswer(int userAnswer) {

        updateAnswer(questions.get(question_cursor).getId_question(), userAnswer);

        if (userAnswer == questions.get(question_cursor).getRight_answer()) {
            questionsToEnd--;
            question_cursor++;
            Log.d("myLogs", "User answered right");
            if (questionsToEnd > 0) {
                sendQuestionToTheView();
            } else {
                Log.d("myLogs", "All answered");
                sendInfoToUserStat.execute();
                question_cursor++;
                getViewState().userWin();
            }
        } else {
            Log.d("myLogs", "User answered wrong and he lose");
            is_lose = true;
            sendInfoToUserStat.execute();
            getViewState().userLose(question_cursor);
        }
    }

    private void updateAnswer(int id_question, int userAnswer) {
        NetworkService.getInstance().getJSONApi().updateAnswer(id_question, userAnswer).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("myLogs", "maybe updated");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("myLogs", "MISTAKE");
            }
        });
    }
}


/*

private int randomElement() {
        // while calling, returns
        int randomIndex = new Random().nextInt(idArray.size());
        int element = idArray.get(randomIndex);
        idArray.remove(randomIndex);
        Log.d("myLogs", "size = " + idArray.size());
        idArray.trimToSize();
        return element;
    }


    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, ArrayList<Integer>> getArrayOfSuitableIds = new AsyncTask<Void, Void, ArrayList<Integer>>() {
        @Override
        protected ArrayList<Integer> doInBackground(Void... voids) {

            ArrayList<Integer> tempArray = new ArrayList<>();
            databaseHelper = new DatabaseHelper(context);
            try {
                database = databaseHelper.open();
                cursor = database.query(DatabaseHelper.QTABLE, new String[]{DatabaseHelper.COLUMN_Q_ID_QUESTION},
                        DatabaseHelper.COLUMN_Q_LEXEL + "=?", new String[]{String.valueOf(level)},
                        null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) do {
                        Log.d("myLogs", "suitable ID = " + cursor.getInt(0));
                        tempArray.add(cursor.getInt(0));
                    } while (cursor.moveToNext());
                    cursor.close();
                }

                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("myLogs", "SQLException ");
            }
            return tempArray;
        }

        @Override
        protected void onPostExecute(ArrayList<Integer> tempArray) {
            super.onPostExecute(tempArray);
            idArray = tempArray;  // here gets all (maybe more then 10)
            getQuestionsFromDB.execute();
        }
    };

    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Integer, Void, Integer> getQuestionsFromDB = new AsyncTask<Integer, Void, Integer>() {
        @Override
        protected Integer doInBackground(Integer... integers) {

            String[] columns = {DatabaseHelper.COLUMN_Q_ID_QUESTION, DatabaseHelper.COLUMN_Q_QUESTION_TEXT, DatabaseHelper.COLUMN_Q_ANSWER_ONE,
                    DatabaseHelper.COLUMN_Q_ANSWER_TWO, DatabaseHelper.COLUMN_Q_ANSWER_THREE,
                    DatabaseHelper.COLUMN_Q_ANSWER_FOUR, DatabaseHelper.COLUMN_Q_RIGHT_ANSWER,
                    DatabaseHelper.COLUMN_Q_CATEGORY};        // 8 columns

            databaseHelper = new DatabaseHelper(context);
            try {
                database = databaseHelper.open();

                String[] id = new String[questionsToEnd];
                for (int i = 0; i < questionsToEnd; i++) {
                    int temp = randomElement();
                    id[i] = String.valueOf(temp);
                    Log.d("myLogs", "id[" + i + "] = " + id[i]);
                }
                Log.d("myLogs", "YES");

                for (int i = 0; i < questionsToEnd; i++) {
                    cursor = database.query(DatabaseHelper.QTABLE, columns, DatabaseHelper.COLUMN_Q_ID_QUESTION + "=" + id[i],
                            null, null, null, null);

                    int ID = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ID_QUESTION);
                    int TEXT = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_QUESTION_TEXT);
                    int A1 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_ONE);
                    int A2 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_TWO);
                    int A3 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_THREE);
                    int A4 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_FOUR);
                    int RIGHT = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_RIGHT_ANSWER);
                    int CAT = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_CATEGORY);

                    if (cursor.moveToFirst()) do {

                        Log.d("myLogs", "Cursor step # " + i);
                        questions[i].id = cursor.getInt(ID);
                        questions[i].text = cursor.getString(TEXT);
                        questions[i].answer1 = cursor.getString(A1);
                        questions[i].answer2 = cursor.getString(A2);
                        questions[i].answer3 = cursor.getString(A3);
                        questions[i].answer4 = cursor.getString(A4);
                        questions[i].rightAnswer = cursor.getInt(RIGHT);
                        questions[i].category = cursor.getString(CAT);
                    } while (cursor.moveToNext());
                    counter_of_questions_from_DB = i;
                    cursor.close();
                }

                database.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            databaseHelper.close();

            return counter_of_questions_from_DB;
        }

        @Override
        protected void onPostExecute(Integer number_of_questions_from_DB) {
            super.onPostExecute(number_of_questions_from_DB);

            if (number_of_questions_from_DB + 1 < questionsToEnd)
                questionsToEnd = number_of_questions_from_DB;
            sendQuestionToTheView();
        }
    };
*/

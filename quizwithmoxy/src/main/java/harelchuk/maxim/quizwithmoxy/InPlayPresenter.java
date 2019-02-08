package harelchuk.maxim.quizwithmoxy;

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
import java.util.ArrayList;
import java.util.Random;

@InjectViewState
public class InPlayPresenter extends MvpPresenter<InPlayView> {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private SharedPreferences sharedPreferences;


    private Cursor cursor;

    private Context context;

    private ArrayList<Integer> idArray;

    private int level;
    private int questionsToEnd;
    private int rightAnswer;
    private int counter_of_questions_from_DB;
    private int question_cursor;
    private int addScore;

    private boolean is_lose;

    private QuestionClass[] questions;


    InPlayPresenter() {
        this.context = getContext();
        getLevel();
        this.idArray = new ArrayList<>();
        this.questionsToEnd = 10;
        this.question_cursor = 0;
        this.questions = new QuestionClass[10];
        for (int i = 0; i < 10; i++) {
            questions[i] = new QuestionClass();
        }
        this.counter_of_questions_from_DB = 0;

        this.is_lose = false;
        Log.d("myLogs", "InPlayPresenter const");
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
        getViewState().showQuestion(questionsToEnd, questions[question_cursor].text, questions[question_cursor].answer1,
                questions[question_cursor].answer2, questions[question_cursor].answer3, questions[question_cursor].answer4,
                questions[question_cursor].category);
    }


    private void getLevel() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.level = sharedPreferences.getInt("level", 0) + 1;
        Log.d("myLogs", "Level in presenter = " + level);
        getArrayOfSuitableIds.execute();
    }

    private Context getContext() {
        return AppForContext.getContext();
    }

    void checkAnswer(int userAnswer) {

        if (questionsToEnd > 1) {

            if (userAnswer == questions[question_cursor].rightAnswer) {
                questionsToEnd--;
                question_cursor++;
                Log.d("myLogs", "User answered right");
                sendQuestionToTheView();
            } else {
                Log.d("myLogs", "User answered wrong and he lose");
                //sendQuestionToTheView();
                is_lose = true;
                sendInfoToUserStat.execute();
                getViewState().userLose(question_cursor);
            }

        } else {
            Log.d("myLogs", "All answered");
            sendInfoToUserStat.execute();
            question_cursor++;
            getViewState().userWin();
        }
    }

    private int randomElement() {
        // while calling, returns
        int randomIndex = new Random().nextInt(idArray.size());
        int element = idArray.get(randomIndex);
        idArray.remove(randomIndex);
        Log.d("myLogs", "size = " + idArray.size());
        idArray.trimToSize();
        return element;
    }

}

class QuestionClass {

    int id;
    String text;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int rightAnswer;
    String category;

    QuestionClass() {
        this.id = 0;
        this.text = "0";
        this.answer1 = "0";
        this.answer2 = "0";
        this.answer3 = "0";
        this.answer4 = "0";
        this.rightAnswer = 0;
        this.category = "None";
    }

}






/*


                cursor = database.query(DatabaseHelper.QTABLE,columns,DatabaseHelper.COLUMN_Q_ID_QUESTION+" IN (?,?,?,?,?,?,?,?,?,?)",
                        id,null,null,null);

                if (cursor!=null) {


                    int ID = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ID_QUESTION);
                    int TEXT = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_QUESTION_TEXT);
                    int A1 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_ONE);
                    int A2 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_TWO);
                    int A3 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_THREE);
                    int A4 = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_ANSWER_FOUR);
                    int RIGHT = cursor.getColumnIndex(DatabaseHelper.COLUMN_Q_RIGHT_ANSWER);

                    int i=0;

                    if (cursor.moveToFirst()) do {
                        Log.d("myLog","Cursor step # "+i);

                        Log.d("myLog","i =" +i);

                        //Log.d("myLog", questions[i].text);
                        Log.d("myLog",String.valueOf(cursor.getInt(ID)));
                        Log.d("myLog",cursor.getString(TEXT));
                        Log.d("myLog",cursor.getString(A1));

                        questions[i].id = cursor.getInt(ID);
                        questions[i].text=cursor.getString(TEXT);
                        questions[i].answer1=cursor.getString(A1);
                        questions[i].answer2=cursor.getString(A2);
                        questions[i].answer3=cursor.getString(A3);
                        questions[i].answer4=cursor.getString(A4);
                        questions[i].rightAnswer=cursor.getInt(RIGHT);

                        i++;

                    }   while (cursor.moveToNext()) ;
                    cursor.close();
                }
                */






/*
                if(isEmptyIdArray){
                    Log.d("myLogs", "Is isEmptyIdArray");
                    isEmptyIdArray=false;

                    //code here

                } else Log.d("myLogs", "Not isEmptyIdArray");
*/


/*
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, String> showMyName = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {

                String myName = null;
                databaseHelper = new DatabaseHelper(AppForContext.getContext());

                try {
                    database =databaseHelper.open();
                    cursor = database.query(DatabaseHelper.UTABLE, new String[]{DatabaseHelper.COLUMN_U_USER_NAME},null,
                                    null,null,null,null);

                    if (cursor!=null) {
                        if (cursor.moveToFirst()) {
                            myName = cursor.getString(0);
                            Log.d("myLogs", myName);
                        } while (cursor.moveToNext()) ;
                        cursor.close();
                    }
                    database.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                databaseHelper.close();
                return myName;
            }

            @Override
            protected void onPostExecute(String result) {
                Log.d("myLogs", "Async success " + result);
            }
        };
        showMyName.execute();
        */
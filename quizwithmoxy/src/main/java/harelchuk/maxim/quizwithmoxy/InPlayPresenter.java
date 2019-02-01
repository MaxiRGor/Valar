package harelchuk.maxim.quizwithmoxy;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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

    private Cursor cursor;

    private ArrayList<Integer> idArray;

    private int level;
    private int questionsToEnd;
    private int rightAnswer;
    private int counter_of_questions_from_DB;
    private int question_cursor;

    private QuestionClass[] questions;


    InPlayPresenter() {
        this.idArray = new ArrayList<>();
        this.questionsToEnd = 10;
        this.question_cursor = 0;
        this.questions = new QuestionClass[10];
        for (int i = 0; i < 10; i++) {
            questions[i] = new QuestionClass();
        }
        this.counter_of_questions_from_DB = 0;
        this.databaseHelper = new DatabaseHelper(AppForContext.getContext());
        Log.d("myLogs", "InPlayPresenter const");
    }

    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, ArrayList<Integer>> getArrayOfSuitableIds = new AsyncTask<Void, Void, ArrayList<Integer>>() {
        @Override
        protected ArrayList<Integer> doInBackground(Void... voids) {

            ArrayList<Integer> tempArray = new ArrayList<>();

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
                    DatabaseHelper.COLUMN_Q_ANSWER_FOUR, DatabaseHelper.COLUMN_Q_RIGHT_ANSWER};        // 7 columns

            try {
                database = databaseHelper.open();

                String[] id = new String[questionsToEnd];
                for (int i = 0; i < questionsToEnd; i++) {
                    int temp = randomElement(idArray);
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

                    if (cursor.moveToFirst()) do {

                        Log.d("myLogs", "Cursor step # " + i);
                        questions[i].id = cursor.getInt(ID);
                        questions[i].text = cursor.getString(TEXT);
                        questions[i].answer1 = cursor.getString(A1);
                        questions[i].answer2 = cursor.getString(A2);
                        questions[i].answer3 = cursor.getString(A3);
                        questions[i].answer4 = cursor.getString(A4);
                        questions[i].rightAnswer = cursor.getInt(RIGHT);
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

    private void sendQuestionToTheView() {
        getViewState().showQuestion(questionsToEnd, questions[question_cursor].text, questions[question_cursor].answer1,
                questions[question_cursor].answer2, questions[question_cursor].answer3, questions[question_cursor].answer4);
    }


    void getLevel(int level_chosen) {
        this.level = level_chosen;
        Log.d("myLogs", "Level in presenter = " + level);
        getArrayOfSuitableIds.execute();
    }

    void checkAnswer(int userAnswer) {

        if (questionsToEnd > 1) {

            if (userAnswer == questions[question_cursor].rightAnswer) {
                questionsToEnd--;
                question_cursor++;
                Log.d("myLogs", "User answered right");
                sendQuestionToTheView();
            } else {
                //questionsToEnd--;
                //question_cursor++;
                Log.d("myLogs", "User answered wrong");
                //sendQuestionToTheView();
                getViewState().userLose(question_cursor);
            }

        } else {
            Log.d("myLogs", "All answered");
            getViewState().userWin();

        }
    }

    private int randomElement(ArrayList<Integer> arrayList) {
        // while calling, returns
        int randomIndex = new Random().nextInt(arrayList.size());
        //this.idArray.remove(randomIndex);
        return arrayList.get(randomIndex);
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

    QuestionClass() {
        this.id = 0;
        this.text = "0";
        this.answer1 = "0";
        this.answer2 = "0";
        this.answer3 = "0";
        this.answer4 = "0";
        this.rightAnswer = 0;
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
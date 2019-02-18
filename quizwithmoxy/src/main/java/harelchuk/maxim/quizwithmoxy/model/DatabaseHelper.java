package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH; // full way to DB
    private static String DB_NAME = "quizformoxy.db";
    private static final int SCHEMA = 1; // DB version

    public static final String QTABLE = "qtable";

    public static final String COLUMN_Q_ID_QUESTION = "id_question";
    public static final String COLUMN_Q_QUESTION_TEXT = "question_text";
    public static final String COLUMN_Q_ANSWER_ONE = "answer_one";
    public static final String COLUMN_Q_ANSWER_TWO = "answer_two";
    public static final String COLUMN_Q_ANSWER_THREE = "answer_three";
    public static final String COLUMN_Q_ANSWER_FOUR = "answer_four";
    public static final String COLUMN_Q_RIGHT_ANSWER = "right_answer";
    public static final String COLUMN_Q_LEXEL = "level";
    public static final String COLUMN_Q_IN_BOOK = "in_book";
    public static final String COLUMN_Q_IN_SERIAL = "in_serial";
    public static final String COLUMN_Q_CATEGORY = "category";
    public static final String COLUMN_Q_SCORE = "score";
    public static final String COLUMN_Q_IS_ANSWERED = "is_answered";
    public static final String COLUMN_Q_USER_ANSWER = "user_answer";


    public static final String UTABLE = "utable";

    public static final String COLUMN_U_USER_NAME = "user_name";
    public static final String COLUMN_U_LEVEL = "level";
    public static final String COLUMN_U_SCORE = "score";
    public static final String COLUMN_U_NUMBER_OF_ANSWERS = "nuber_of_answers";
    public static final String COLUMN_U_NUMBER_OF_GAMES = "number_of_games";
    public static final String COLUMN_U_PERCENT_OF_RIGHT = "percent_of_right";

    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //no need
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void create_db() {
        InputStream myInput;
        OutputStream myOutput;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();

                //get local DB as a stream
                myInput = myContext.getAssets().open(DB_NAME);

                // path to new DB
                String outFileName = DB_PATH;

                // open empty DB
                myOutput = new FileOutputStream(outFileName);

                // copy
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException ex) {
            Log.d("myLogs", ex.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

}
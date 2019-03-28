package harelchuk.maxim.quizwithmoxy.presenter;

import android.preference.PreferenceManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import harelchuk.maxim.quizwithmoxy.model.AppForContext;
import harelchuk.maxim.quizwithmoxy.model.CoinConversation;
import harelchuk.maxim.quizwithmoxy.model.NetworkService;
import harelchuk.maxim.quizwithmoxy.model.Question;
import harelchuk.maxim.quizwithmoxy.model.UserDataSingleton;
import harelchuk.maxim.quizwithmoxy.view.InPlayView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class InPlayPresenter extends MvpPresenter<InPlayView> {

    private int levelUserChoose;
    private boolean is_book;
    private boolean is_serial;

    private int questionsToEnd;
    private int questionCursor;
    private boolean is_lose;
    private List<Question> questions;

    public InPlayPresenter() {
        Log.d("myLogs", "InPlayPresenter const");
        this.is_book = UserDataSingleton.getInstance().isIs_books();
        this.is_serial = UserDataSingleton.getInstance().isIs_films();
        this.levelUserChoose = UserDataSingleton.getInstance().getChosen_level();
        this.questionsToEnd = 7;
        this.questionCursor = 0;
        this.is_lose = false;
        getQuestionsByLevelFromServer(levelUserChoose);
        getViewState().findElement();
    }


    private void sendQuestionToTheView() {
        getViewState().showQuestion(questionsToEnd, questions.get(questionCursor).getQuestion_text(),
                questions.get(questionCursor).getAnswer_one(), questions.get(questionCursor).getAnswer_two(),
                questions.get(questionCursor).getAnswer_three(), questions.get(questionCursor).getAnswer_four(),
                questions.get(questionCursor).getCategory(), questions.get(questionCursor).getLevel(),
                questions.get(questionCursor).getIn_book(), questions.get(questionCursor).getIn_serial());
    }


    private void getQuestionsByLevelFromServer(int lvl) {
        NetworkService.getInstance().getJSONApi().getByLevel(lvl, is_book, is_serial).enqueue(new Callback<List<Question>>() {
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
                getViewState().showFailure();
            }
        });
    }

    public void checkAnswer(int userAnswer) {

        updateAnswerOnServer(questions.get(questionCursor).getId_question(), userAnswer);

        if (userAnswer == questions.get(questionCursor).getRight_answer()) {
            questionsToEnd--;
            questionCursor++;
            Log.d("myLogs", "User answered right");
            if (questionsToEnd > 0) {
                sendQuestionToTheView();
            } else {
                Log.d("myLogs", "All answered");
                questionCursor++;
                getViewState().userWin();
            }
        } else {
            Log.d("myLogs", "User answered wrong and he lose");
            is_lose = true;
            getViewState().userLose(questionCursor);
        }
    }

    public void sendInfoToUserStat() {

        long coinsToAdd = coinsToAdd(levelUserChoose, is_lose);
        UserDataSingleton.getInstance().addUserMoney(coinsToAdd);

        if (levelUserChoose == 1 || levelUserChoose == 2 || levelUserChoose == 3) {
            UserDataSingleton.getInstance().incrementNumber_easy_games();
            if (!is_lose) {
                UserDataSingleton.getInstance().incrementNumber_easy_winnings();
            }
        }
        if (levelUserChoose == 4 || levelUserChoose == 5 || levelUserChoose == 6) {
            UserDataSingleton.getInstance().incrementNumber_medium_games();
            if (!is_lose) {
                UserDataSingleton.getInstance().incrementNumber_medium_winnings();
            }
        }
        if (levelUserChoose == 7 || levelUserChoose == 8 || levelUserChoose == 9 || levelUserChoose == 10) {
            UserDataSingleton.getInstance().incrementNumber_hard_games();
            if (!is_lose) {
                UserDataSingleton.getInstance().incrementNumber_hard_winnings();
            }
        }

        long[] coinsGAC = CoinConversation.coins_GD_AD_CP(coinsToAdd);
        getViewState().showAddedScore((int) coinsGAC[0], (int) coinsGAC[1], (int) coinsGAC[2]);
    }

    private void updateAnswerOnServer(int id_question, int userAnswer) {
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

    public void sendFailToUserStat() {

        long moneyToReturn = 0;

        if (levelUserChoose == 1) {
            moneyToReturn = UserDataSingleton.getInstance().getL_1_cost_cp();
        }
        if (levelUserChoose == 2) {
            moneyToReturn = UserDataSingleton.getInstance().getL_2_cost_cp();
        }
        if (levelUserChoose == 3) {
            moneyToReturn = UserDataSingleton.getInstance().getL_3_cost_cp();
        }
        if (levelUserChoose == 4) {
            moneyToReturn = UserDataSingleton.getInstance().getL_4_cost_ad() * 56;
        }
        if (levelUserChoose == 5) {
            moneyToReturn = UserDataSingleton.getInstance().getL_5_cost_ad() * 56;
        }
        if (levelUserChoose == 6) {
            moneyToReturn = UserDataSingleton.getInstance().getL_6_cost_ad() * 56;
        }
        if (levelUserChoose == 7) {
            moneyToReturn = UserDataSingleton.getInstance().getL_7_cost_gd() * 11720;
        }
        if (levelUserChoose == 8) {
            moneyToReturn = UserDataSingleton.getInstance().getL_8_cost_gd() * 11720;
        }
        if (levelUserChoose == 9) {
            moneyToReturn = UserDataSingleton.getInstance().getL_9_cost_gd() * 11720;
        }
        if (levelUserChoose == 10) {
            moneyToReturn = UserDataSingleton.getInstance().getL_10_cost_gd() * 11720;
        }

        UserDataSingleton.getInstance().addUserMoney(moneyToReturn);

        long[] coinsToReturn = CoinConversation.coins_GD_AD_CP(moneyToReturn);
        getViewState().showAddedScore((int) coinsToReturn[0], (int) coinsToReturn[1], (int) coinsToReturn[2]);
    }

    private long coinsToAdd(int level, boolean is_lose) {
        long coins = 0;
        if (is_lose) {
            if (level == 1) {
                coins = (UserDataSingleton.getInstance().getL_1_lose_cp());
            }
            if (level == 2) {
                coins = (UserDataSingleton.getInstance().getL_2_lose_cp());
            }
            if (level == 3) {
                coins = (UserDataSingleton.getInstance().getL_3_lose_cp());
            }
            if (level == 4) {
                coins = (UserDataSingleton.getInstance().getL_4_lose_cp());
            }
            if (level == 5) {
                coins = (UserDataSingleton.getInstance().getL_5_lose_ad() * 56);
            }
            if (level == 6) {
                coins = (UserDataSingleton.getInstance().getL_6_lose_ad() * 56);
            }
            if (level == 7) {
                coins = (UserDataSingleton.getInstance().getL_7_lose_ad() * 56);
            }
            if (level == 8) {
                coins = (UserDataSingleton.getInstance().getL_8_lose_gd() * 11760);
            }
            if (level == 9) {
                coins = (UserDataSingleton.getInstance().getL_9_lose_gd() * 11760);
            }
            if (level == 10) {
                coins = (UserDataSingleton.getInstance().getL_10_lose_gd() * 11760);
            }
        } else {
            if (level == 1) {
                coins = (UserDataSingleton.getInstance().getL_1_reward_cp());
            }
            if (level == 2) {
                coins = (UserDataSingleton.getInstance().getL_2_reward_cp());
            }
            if (level == 3) {
                coins = (UserDataSingleton.getInstance().getL_3_reward_cp());
            }
            if (level == 4) {
                coins = (UserDataSingleton.getInstance().getL_4_reward_ad() * 56);
            }
            if (level == 5) {
                coins = (UserDataSingleton.getInstance().getL_5_reward_ad() * 56);
            }
            if (level == 6) {
                coins = (UserDataSingleton.getInstance().getL_6_reward_ad() * 56);
            }
            if (level == 7) {
                coins = (UserDataSingleton.getInstance().getL_7_reward_gd() * 11760);
            }
            if (level == 8) {
                coins = (UserDataSingleton.getInstance().getL_8_reward_gd() * 11760);
            }
            if (level == 9) {
                coins = (UserDataSingleton.getInstance().getL_9_reward_gd() * 11760);
            }
            if (level == 10) {
                coins = (UserDataSingleton.getInstance().getL_10_reward_gd() * 11760);
            }
        }
        return coins;
    }
}

/*
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

        if (is_lose) addScore = levelUserChoose * questionCursor;
        else addScore = levelUserChoose * questionCursor * 10;

        scr += addScore;

        add_noa = questionCursor;
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
        Log.d("MyLogs", "levelUserChoose " + lvl);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_U_LEVEL, lvl);
        contentValues.put(DatabaseHelper.COLUMN_U_SCORE, scr);
        contentValues.put(DatabaseHelper.COLUMN_U_NUMBER_OF_ANSWERS, noa);
        contentValues.put(DatabaseHelper.COLUMN_U_NUMBER_OF_GAMES, nog);
        return contentValues;
    }
*/

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
                        DatabaseHelper.COLUMN_Q_LEXEL + "=?", new String[]{String.valueOf(levelUserChoose)},
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

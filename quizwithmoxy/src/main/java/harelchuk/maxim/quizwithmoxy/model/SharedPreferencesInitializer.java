package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

public class SharedPreferencesInitializer {

    public static final String SHARED_PREFERENCES_MONEY = "moneyValues";
    public static final String SHARED_PREFERENCES_USER= "userStatistics";

    public static final String L_1_COST_CP = "L_1_COST_CP";
    public static final String L_2_COST_CP = "L_2_COST_CP";
    public static final String L_3_COST_CP = "L_3_COST_CP";
    public static final String L_4_COST_AD = "L_4_COST_AD";
    public static final String L_5_COST_AD = "L_5_COST_AD";
    public static final String L_6_COST_AD = "L_6_COST_AD";
    public static final String L_7_COST_GD = "L_7_COST_GD";
    public static final String L_8_COST_GD = "L_8_COST_GD";
    public static final String L_9_COST_GD = "L_9_COST_GD";
    public static final String L_10_COST_GD = "L_10_COST_GD";

    public static final String L_1_REWARD_CP = "L_1_REWARD_CP";
    public static final String L_2_REWARD_CP = "L_2_REWARD_CP";
    public static final String L_3_REWARD_CP = "L_3_REWARD_CP";
    public static final String L_4_REWARD_AD = "L_4_REWARD_AD";
    public static final String L_5_REWARD_AD = "L_5_REWARD_AD";
    public static final String L_6_REWARD_AD = "L_6_REWARD_AD";
    public static final String L_7_REWARD_GD = "L_7_REWARD_GD";
    public static final String L_8_REWARD_GD = "L_8_REWARD_GD";
    public static final String L_9_REWARD_GD = "L_9_REWARD_GD";
    public static final String L_10_REWARD_GD = "L_10_REWARD_GD";

    public static final String L_1_LOSE_CP = "L_1_LOSE_CP";
    public static final String L_2_LOSE_CP = "L_2_LOSE_CP";
    public static final String L_3_LOSE_CP = "L_3_LOSE_CP";
    public static final String L_4_LOSE_CP = "L_4_LOSE_CP";
    public static final String L_5_LOSE_AD = "L_5_LOSE_AD";
    public static final String L_6_LOSE_AD = "L_6_LOSE_AD";
    public static final String L_7_LOSE_AD = "L_7_LOSE_AD";
    public static final String L_8_LOSE_GD = "L_8_LOSE_GD";
    public static final String L_9_LOSE_GD = "L_9_LOSE_GD";
    public static final String L_10_LOSE_GD = "L_10_LOSE_GD";


    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String MONEY_TEMP = "MONEY_TEMP";
    public static final String MONEY_ALL = "MONEY_ALL";
    public static final String NUMBER_EASY_GAMES = "NUMBER_EASY_GAMES";
    public static final String NUMBER_MEDIUM_GAMES = "NUMBER_MEDIUM_GAMES";
    public static final String NUMBER_HARD_GAMES = "NUMBER_HARD_GAMES";
    public static final String NUMBER_EASY_WINNINGS = "NUMBER_EASY_WINNINGS";
    public static final String NUMBER_MEDIUM_WINNINGS = "NUMBER_MEDIUM_WINNINGS";
    public static final String NUMBER_HARD_WINNINGS = "NUMBER_HARD_WINNINGS";
    public static final String IS_ADV = "IS_ADV";
    public static final String IS_BOOKS = "IS_BOOKS";
    public static final String IS_FILMS = "IS_FILMS";
    public static final String IS_SKIN_TARGAR = "IS_SKIN_TARGAR";
    public static final String IS_SKIN_STARK = "IS_SKIN_STARK";
    public static final String IS_SKIN_LANN = "IS_SKIN_LANN";
    public static final String IS_SKIN_NIGHT = "IS_SKIN_NIGHT";
    public static final String IS_CREDIT = "IS_CREDIT";
    public static final String CREDIT_TIME = "CREDIT_TIME";
    public static final String CREDIT_SUM = "CREDIT_SUM";
    public static final String IS_DEBIT = "IS_DEBIT";
    public static final String DEBIT_TIME = "DEBIT_TIME";
    public static final String DEBIT_SUM = "DEBIT_SUM";


    public static void setSharedPreferencesMoney(Context context) {

        SharedPreferences sharedPreferencesMoney = context.getSharedPreferences(SHARED_PREFERENCES_MONEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        if(!sharedPreferencesMoney.contains("initialized")) {

            Log.d("myLogs", "SP M CREATED");

            editor = sharedPreferencesMoney.edit();

            editor.putBoolean("initialized", true);

            editor.putInt(L_1_COST_CP, 1);
            editor.putInt(L_2_COST_CP, 5);
            editor.putInt(L_3_COST_CP, 15);
            editor.putInt(L_4_COST_AD, 1);
            editor.putInt(L_5_COST_AD, 8);
            editor.putInt(L_6_COST_AD, 25);
            editor.putInt(L_7_COST_GD, 1);
            editor.putInt(L_8_COST_GD, 7);
            editor.putInt(L_9_COST_GD, 21);
            editor.putInt(L_10_COST_GD, 150);


            editor.putInt(L_1_REWARD_CP, 11);
            editor.putInt(L_2_REWARD_CP, 35);
            editor.putInt(L_3_REWARD_CP, 127);
            editor.putInt(L_4_REWARD_AD, 25);
            editor.putInt(L_5_REWARD_AD, 83);
            editor.putInt(L_6_REWARD_AD, 655);
            editor.putInt(L_7_REWARD_GD, 29);
            editor.putInt(L_8_REWARD_GD, 91);
            editor.putInt(L_9_REWARD_GD, 321);
            editor.putInt(L_10_REWARD_GD, 1000);

            editor.putInt(L_1_LOSE_CP, 1);
            editor.putInt(L_2_LOSE_CP, 2);
            editor.putInt(L_3_LOSE_CP, 10);
            editor.putInt(L_4_LOSE_CP, 30);
            editor.putInt(L_5_LOSE_AD, 3);
            editor.putInt(L_6_LOSE_AD, 24);
            editor.putInt(L_7_LOSE_AD, 75);
            editor.putInt(L_8_LOSE_GD, 4);
            editor.putInt(L_9_LOSE_GD, 28);
            editor.putInt(L_10_LOSE_GD, 63);

            editor.apply();
        }
    }

    public static void setSharedPreferencesUser(Context context) {

        SharedPreferences sharedPreferencesUser = context.getSharedPreferences(SHARED_PREFERENCES_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        if(!sharedPreferencesUser.contains("initialized")) {

            Log.d("myLogs", "SP U CREATED");

            editor = sharedPreferencesUser.edit();

            editor.putBoolean("initialized", true);

            String uniqueID = UUID.randomUUID().toString();

            editor.putString(USER_ID,uniqueID);
            editor.putString(USER_NAME,"Great Player");

            editor.putLong(MONEY_TEMP,6123456);            //      test version, need 3
            editor.putLong(MONEY_ALL,16123456);             //      test version, need 3

            editor.putInt(NUMBER_EASY_GAMES,0);
            editor.putInt(NUMBER_MEDIUM_GAMES,0);
            editor.putInt(NUMBER_HARD_GAMES,0);

            editor.putInt(NUMBER_EASY_WINNINGS,0);
            editor.putInt(NUMBER_MEDIUM_WINNINGS,0);
            editor.putInt(NUMBER_HARD_WINNINGS,0);

            editor.putBoolean(IS_ADV,true);

            editor.putBoolean(IS_BOOKS,false);
            editor.putBoolean(IS_FILMS,true);

            editor.putBoolean(IS_SKIN_TARGAR,true);
            editor.putBoolean(IS_SKIN_STARK,false);
            editor.putBoolean(IS_SKIN_LANN,false);
            editor.putBoolean(IS_SKIN_NIGHT,false);

            editor.putBoolean(IS_CREDIT,false);
            editor.putLong(CREDIT_TIME,0);
            editor.putLong(CREDIT_SUM,0);

            editor.putBoolean(IS_DEBIT,false);
            editor.putLong(DEBIT_TIME,0);
            editor.putLong(DEBIT_SUM,0);

            editor.apply();
        }
    }



}

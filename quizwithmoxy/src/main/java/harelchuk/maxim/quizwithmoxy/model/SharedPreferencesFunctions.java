package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.content.SharedPreferences;

import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_10_LOSE_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_10_REWARD_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_1_LOSE_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_1_REWARD_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_2_LOSE_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_2_REWARD_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_3_LOSE_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_3_REWARD_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_4_LOSE_CP;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_4_REWARD_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_5_LOSE_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_5_REWARD_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_6_LOSE_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_6_REWARD_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_7_LOSE_AD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_7_REWARD_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_8_LOSE_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_8_REWARD_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_9_LOSE_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.L_9_REWARD_GD;
import static harelchuk.maxim.quizwithmoxy.model.SharedPreferencesInitializer.SHARED_PREFERENCES_MONEY;

public class SharedPreferencesFunctions {

    public int[] money_and_type(int level, boolean is_lose){
        SharedPreferences moneySP = AppForContext.getContext().
                getSharedPreferences(SHARED_PREFERENCES_MONEY, Context.MODE_PRIVATE);
        int money_add = 0;
        int money_type = 0;
        if (is_lose) {
            if (level == 1) {
                money_add = moneySP.getInt(L_1_LOSE_CP, 0);
                money_type = 1;
            }
            if (level == 2) {
                money_add = moneySP.getInt(L_2_LOSE_CP, 0);
                money_type = 1;
            }
            if (level == 3) {
                money_add = moneySP.getInt(L_3_LOSE_CP, 0);
                money_type = 1;
            }
            if (level == 4) {
                money_add = moneySP.getInt(L_4_LOSE_CP, 0);
                money_type = 1;
            }
            if (level == 5) {
                money_add = moneySP.getInt(L_5_LOSE_AD, 0);
                money_type = 2;
            }
            if (level == 6) {
                money_add = moneySP.getInt(L_6_LOSE_AD, 0);
                money_type = 2;
            }
            if (level == 7) {
                money_add = moneySP.getInt(L_7_LOSE_AD, 0);
                money_type = 2;
            }
            if (level == 8) {
                money_add = moneySP.getInt(L_8_LOSE_GD, 0);
                money_type = 3;
            }
            if (level == 9) {
                money_add = moneySP.getInt(L_9_LOSE_GD, 0);
                money_type = 3;
            }
            if (level == 10) {
                money_add = moneySP.getInt(L_10_LOSE_GD, 0);
                money_type = 3;
            }
        } else {
            if (level == 1) {
                money_add = moneySP.getInt(L_1_REWARD_CP, 0);
                money_type = 1;
            }
            if (level == 2) {
                money_add = moneySP.getInt(L_2_REWARD_CP, 0);
                money_type = 1;
            }
            if (level == 3) {
                money_add = moneySP.getInt(L_3_REWARD_CP, 0);
                money_type = 1;
            }
            if (level == 4) {
                money_add = moneySP.getInt(L_4_REWARD_AD, 0);
                money_type = 2;
            }
            if (level == 5) {
                money_add = moneySP.getInt(L_5_REWARD_AD, 0);
                money_type = 2;
            }
            if (level == 6) {
                money_add = moneySP.getInt(L_6_REWARD_AD, 0);
                money_type = 2;
            }
            if (level == 7) {
                money_add = moneySP.getInt(L_7_REWARD_GD, 0);
                money_type = 3;
            }
            if (level == 8) {
                money_add = moneySP.getInt(L_8_REWARD_GD, 0);
                money_type = 3;
            }
            if (level == 9) {
                money_add = moneySP.getInt(L_9_REWARD_GD, 0);
                money_type = 3;
            }
            if (level == 10) {
                money_add = moneySP.getInt(L_10_REWARD_GD, 0);
                money_type = 3;
            }
        }
        return new int[]{money_add,money_type};
    }

    public long[] coins_GD_AD_CP(long money){
        long coins_GD;
        long coins_AD;
        long coins_CP;
        int conversation_CP_GD=11760;
        int conversation_CP_AD=56;
        coins_GD=money/(conversation_CP_GD);
        money-=coins_GD*conversation_CP_GD;
        coins_AD=money/(conversation_CP_AD);
        money-=coins_AD*conversation_CP_AD;
        coins_CP=money;
        return new long[]{coins_GD,coins_AD,coins_CP};
    }
}

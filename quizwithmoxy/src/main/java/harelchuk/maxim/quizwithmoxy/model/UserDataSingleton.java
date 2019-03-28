package harelchuk.maxim.quizwithmoxy.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

import static harelchuk.maxim.quizwithmoxy.model.STRINGS.*;

public class UserDataSingleton {

    private SharedPreferences sharedPreferencesMoney;

    private int l_1_cost_cp;
    private int l_2_cost_cp;
    private int l_3_cost_cp;
    private int l_4_cost_ad;
    private int l_5_cost_ad;
    private int l_6_cost_ad;
    private int l_7_cost_gd;
    private int l_8_cost_gd;
    private int l_9_cost_gd;
    private int l_10_cost_gd;

    private int l_1_reward_cp;
    private int l_2_reward_cp;
    private int l_3_reward_cp;
    private int l_4_reward_ad;
    private int l_5_reward_ad;
    private int l_6_reward_ad;
    private int l_7_reward_gd;
    private int l_8_reward_gd;
    private int l_9_reward_gd;
    private int l_10_reward_gd;

    private int l_1_lose_cp;
    private int l_2_lose_cp;
    private int l_3_lose_cp;
    private int l_4_lose_cp;
    private int l_5_lose_ad;
    private int l_6_lose_ad;
    private int l_7_lose_ad;
    private int l_8_lose_gd;
    private int l_9_lose_gd;
    private int l_10_lose_gd;


    private SharedPreferences sharedPreferencesUser;

    private String user_id;
    private String user_name;

    private long user_money;


    private int number_easy_games;
    private int number_medium_games;
    private int number_hard_games;

    private int number_easy_winnings;
    private int number_medium_winnings;
    private int number_hard_winnings;

    private boolean is_adv;

    private boolean is_books;
    private boolean is_films;

    private int current_theme;
    private boolean is_skin_targar;
    private boolean is_skin_stark;
    private boolean is_skin_lann;
    private boolean is_skin_night;

    private boolean is_credit;
    private long credit_time;
    private long credit_sum;

    private boolean is_debit;
    private long debit_time;
    private long debit_sum;

    private int chosen_level;

    private static final UserDataSingleton ourInstance = new UserDataSingleton();

    public static UserDataSingleton getInstance() {
        return ourInstance;
    }

    private UserDataSingleton() {
        Log.d("myLogs", "///////////////Singleton created//////////////////////////////");

        setSharedPreferencesMoneyIfNotExists();
        setSharedPreferencesUserIfNotExists();
        getVariables();
    }

    private void getVariables() {

        this.l_1_cost_cp = sharedPreferencesMoney.getInt(L_1_COST_CP, 0);
        this.l_2_cost_cp = sharedPreferencesMoney.getInt(L_2_COST_CP, 0);
        this.l_3_cost_cp = sharedPreferencesMoney.getInt(L_3_COST_CP, 0);
        this.l_4_cost_ad = sharedPreferencesMoney.getInt(L_4_COST_AD, 0);
        this.l_5_cost_ad = sharedPreferencesMoney.getInt(L_5_COST_AD, 0);
        this.l_6_cost_ad = sharedPreferencesMoney.getInt(L_6_COST_AD, 0);
        this.l_7_cost_gd = sharedPreferencesMoney.getInt(L_7_COST_GD, 0);
        this.l_8_cost_gd = sharedPreferencesMoney.getInt(L_8_COST_GD, 0);
        this.l_9_cost_gd = sharedPreferencesMoney.getInt(L_9_COST_GD, 0);
        this.l_10_cost_gd = sharedPreferencesMoney.getInt(L_10_COST_GD, 0);

        this.l_1_reward_cp = sharedPreferencesMoney.getInt(L_1_REWARD_CP, 0);
        this.l_2_reward_cp = sharedPreferencesMoney.getInt(L_2_REWARD_CP, 0);
        this.l_3_reward_cp = sharedPreferencesMoney.getInt(L_3_REWARD_CP, 0);
        this.l_4_reward_ad = sharedPreferencesMoney.getInt(L_4_REWARD_AD, 0);
        this.l_5_reward_ad = sharedPreferencesMoney.getInt(L_5_REWARD_AD, 0);
        this.l_6_reward_ad = sharedPreferencesMoney.getInt(L_6_REWARD_AD, 0);
        this.l_7_reward_gd = sharedPreferencesMoney.getInt(L_7_REWARD_GD, 0);
        this.l_8_reward_gd = sharedPreferencesMoney.getInt(L_8_REWARD_GD, 0);
        this.l_9_reward_gd = sharedPreferencesMoney.getInt(L_9_REWARD_GD, 0);
        this.l_10_reward_gd = sharedPreferencesMoney.getInt(L_10_REWARD_GD, 0);

        this.l_1_lose_cp = sharedPreferencesMoney.getInt(L_1_LOSE_CP, 0);
        this.l_2_lose_cp = sharedPreferencesMoney.getInt(L_2_LOSE_CP, 0);
        this.l_3_lose_cp = sharedPreferencesMoney.getInt(L_3_LOSE_CP, 0);
        this.l_4_lose_cp = sharedPreferencesMoney.getInt(L_4_LOSE_CP, 0);
        this.l_5_lose_ad = sharedPreferencesMoney.getInt(L_5_LOSE_AD, 0);
        this.l_6_lose_ad = sharedPreferencesMoney.getInt(L_6_LOSE_AD, 0);
        this.l_7_lose_ad = sharedPreferencesMoney.getInt(L_7_LOSE_AD, 0);
        this.l_8_lose_gd = sharedPreferencesMoney.getInt(L_8_LOSE_GD, 0);
        this.l_9_lose_gd = sharedPreferencesMoney.getInt(L_9_LOSE_GD, 0);
        this.l_10_lose_gd = sharedPreferencesMoney.getInt(L_10_LOSE_GD, 0);


        this.user_id = sharedPreferencesUser.getString(USER_ID, "1234567890");
        this.user_name = sharedPreferencesUser.getString(USER_NAME, "PLAYER");

        this.user_money = sharedPreferencesUser.getLong(USER_MONEY, 0);

        this.number_easy_games = sharedPreferencesUser.getInt(NUMBER_EASY_GAMES, 0);
        this.number_medium_games = sharedPreferencesUser.getInt(NUMBER_MEDIUM_GAMES, 0);
        this.number_hard_games = sharedPreferencesUser.getInt(NUMBER_HARD_GAMES, 0);

        this.number_easy_winnings = sharedPreferencesUser.getInt(NUMBER_EASY_WINNINGS, 0);
        this.number_medium_winnings = sharedPreferencesUser.getInt(NUMBER_MEDIUM_WINNINGS, 0);
        this.number_hard_winnings = sharedPreferencesUser.getInt(NUMBER_HARD_WINNINGS, 0);

        this.is_adv = sharedPreferencesUser.getBoolean(IS_ADV, false);

        this.is_books = sharedPreferencesUser.getBoolean(IS_BOOKS, false);
        this.is_films = sharedPreferencesUser.getBoolean(IS_FILMS, true);

        this.current_theme = sharedPreferencesUser.getInt(CURRENT_THEME, 0);
        this.is_skin_targar = sharedPreferencesUser.getBoolean(IS_SKIN_TARGAR, true);
        this.is_skin_stark = sharedPreferencesUser.getBoolean(IS_SKIN_STARK, true);
        this.is_skin_lann = sharedPreferencesUser.getBoolean(IS_SKIN_LANN, true);
        this.is_skin_night = sharedPreferencesUser.getBoolean(IS_SKIN_NIGHT, true);

        this.is_credit = sharedPreferencesUser.getBoolean(IS_CREDIT, false);
        this.credit_time = sharedPreferencesUser.getLong(CREDIT_TIME, 0);
        this.credit_sum = sharedPreferencesUser.getLong(CREDIT_SUM, 0);

        this.is_debit = sharedPreferencesUser.getBoolean(IS_DEBIT, false);
        this.debit_time = sharedPreferencesUser.getLong(DEBIT_TIME, 0);
        this.debit_sum = sharedPreferencesUser.getLong(DEBIT_SUM, 0);

        this.chosen_level=0;
    }

    private void setSharedPreferencesMoneyIfNotExists() {

        this.sharedPreferencesMoney = AppForContext.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_MONEY_AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor;

        if (!sharedPreferencesMoney.contains("initialized")) {

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

            editor.commit();
        }

    }

    private void setSharedPreferencesUserIfNotExists() {

        this.sharedPreferencesUser = AppForContext.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_USER_AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                        , Context.MODE_PRIVATE);

        SharedPreferences.Editor editor;

        if (!sharedPreferencesUser.contains("initialized")) {

            Log.d("myLogs", "SP U CREATED");

            editor = sharedPreferencesUser.edit();

            editor.putBoolean("initialized", true);

            String uniqueID = UUID.randomUUID().toString();

            editor.putString(USER_ID, uniqueID);
            editor.putString(USER_NAME, "Great Player");

            editor.putLong(USER_MONEY, 6123456);            //      test version, need 3

            editor.putInt(NUMBER_EASY_GAMES, 0);
            editor.putInt(NUMBER_MEDIUM_GAMES, 0);
            editor.putInt(NUMBER_HARD_GAMES, 0);

            editor.putInt(NUMBER_EASY_WINNINGS, 0);
            editor.putInt(NUMBER_MEDIUM_WINNINGS, 0);
            editor.putInt(NUMBER_HARD_WINNINGS, 0);

            editor.putBoolean(IS_ADV, true);

            editor.putBoolean(IS_BOOKS, false);
            editor.putBoolean(IS_FILMS, true);

            editor.putInt(CURRENT_THEME, 0);
            editor.putBoolean(IS_SKIN_TARGAR, true);
            editor.putBoolean(IS_SKIN_STARK, false);
            editor.putBoolean(IS_SKIN_LANN, false);
            editor.putBoolean(IS_SKIN_NIGHT, false);

            editor.putBoolean(IS_CREDIT, false);
            editor.putLong(CREDIT_TIME, 0);
            editor.putLong(CREDIT_SUM, 0);

            editor.putBoolean(IS_DEBIT, false);
            editor.putLong(DEBIT_TIME, 0);
            editor.putLong(DEBIT_SUM, 0);

            editor.commit();
        }
    }



    public int getL_1_cost_cp() {
        return l_1_cost_cp;
    }

    public int getL_2_cost_cp() {
        return l_2_cost_cp;
    }

    public int getL_3_cost_cp() {
        return l_3_cost_cp;
    }

    public int getL_4_cost_ad() {
        return l_4_cost_ad;
    }

    public int getL_5_cost_ad() {
        return l_5_cost_ad;
    }

    public int getL_6_cost_ad() {
        return l_6_cost_ad;
    }

    public int getL_7_cost_gd() {
        return l_7_cost_gd;
    }

    public int getL_8_cost_gd() {
        return l_8_cost_gd;
    }

    public int getL_9_cost_gd() {
        return l_9_cost_gd;
    }

    public int getL_10_cost_gd() {
        return l_10_cost_gd;
    }

    public int getL_1_reward_cp() {
        return l_1_reward_cp;
    }

    public int getL_2_reward_cp() {
        return l_2_reward_cp;
    }

    public int getL_3_reward_cp() {
        return l_3_reward_cp;
    }

    public int getL_4_reward_ad() {
        return l_4_reward_ad;
    }

    public int getL_5_reward_ad() {
        return l_5_reward_ad;
    }

    public int getL_6_reward_ad() {
        return l_6_reward_ad;
    }

    public int getL_7_reward_gd() {
        return l_7_reward_gd;
    }

    public int getL_8_reward_gd() {
        return l_8_reward_gd;
    }

    public int getL_9_reward_gd() {
        return l_9_reward_gd;
    }

    public int getL_10_reward_gd() {
        return l_10_reward_gd;
    }

    public int getL_1_lose_cp() {
        return l_1_lose_cp;
    }

    public int getL_2_lose_cp() {
        return l_2_lose_cp;
    }

    public int getL_3_lose_cp() {
        return l_3_lose_cp;
    }

    public int getL_4_lose_cp() {
        return l_4_lose_cp;
    }

    public int getL_5_lose_ad() {
        return l_5_lose_ad;
    }

    public int getL_6_lose_ad() {
        return l_6_lose_ad;
    }

    public int getL_7_lose_ad() {
        return l_7_lose_ad;
    }

    public int getL_8_lose_gd() {
        return l_8_lose_gd;
    }

    public int getL_9_lose_gd() {
        return l_9_lose_gd;
    }

    public int getL_10_lose_gd() {
        return l_10_lose_gd;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public long getUser_money() {
        return user_money;
    }

    public int getNumber_easy_games() {
        return number_easy_games;
    }

    public int getNumber_medium_games() {
        return number_medium_games;
    }

    public int getNumber_hard_games() {
        return number_hard_games;
    }

    public int getNumber_easy_winnings() {
        return number_easy_winnings;
    }

    public int getNumber_medium_winnings() {
        return number_medium_winnings;
    }

    public int getNumber_hard_winnings() {
        return number_hard_winnings;
    }

    public boolean isIs_adv() {
        return is_adv;
    }

    public boolean isIs_books() {
        return is_books;
    }

    public boolean isIs_films() {
        return is_films;
    }

    public int getCurrent_theme() {
        return current_theme;
    }

    public boolean isIs_skin_targar() {
        return is_skin_targar;
    }

    public boolean isIs_skin_lann() {
        return is_skin_lann;
    }

    public boolean isIs_skin_stark() {
        return is_skin_stark;
    }

    public boolean isIs_skin_night() {
        return is_skin_night;
    }

    public boolean isIs_credit() {
        return is_credit;
    }

    public long getCredit_time() {
        return credit_time;
    }

    public long getCredit_sum() {
        return credit_sum;
    }

    public boolean isIs_debit() {
        return is_debit;
    }

    public long getDebit_time() {
        return debit_time;
    }

    public long getDebit_sum() {
        return debit_sum;
    }

    public int getChosen_level() {
        return chosen_level;
    }

    public int setChosen_level(int chosen_level) {
        this.chosen_level = chosen_level;
        return chosen_level;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
        changeSharedPreferenceUserString(USER_ID, user_id);
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        changeSharedPreferenceUserString(USER_NAME, user_name);
    }

    public void addUserMoney(long adding_money) {
        this.user_money += adding_money;
        changeSharedPreferenceUserLong(USER_MONEY, user_money);
    }

    public void setUserMoney(long user_money) {
        this.user_money = user_money;
        changeSharedPreferenceUserLong(USER_MONEY, user_money);
    }

    public void incrementNumber_easy_games() {
        this.number_easy_games++;
        changeSharedPreferenceUserInt(NUMBER_EASY_GAMES, number_easy_games);
    }

    public void incrementNumber_medium_games() {
        this.number_medium_games++;
        changeSharedPreferenceUserInt(NUMBER_MEDIUM_GAMES, number_medium_games);
    }

    public void incrementNumber_hard_games() {
        this.number_hard_games++;
        changeSharedPreferenceUserInt(NUMBER_HARD_GAMES, number_hard_games);
    }

    public void incrementNumber_easy_winnings() {
        this.number_easy_winnings++;
        changeSharedPreferenceUserInt(NUMBER_EASY_WINNINGS, number_easy_winnings);
    }

    public void incrementNumber_medium_winnings() {
        this.number_medium_winnings++;
        changeSharedPreferenceUserInt(NUMBER_MEDIUM_WINNINGS, number_medium_winnings);
    }

    public void incrementNumber_hard_winnings() {
        this.number_hard_winnings++;
        changeSharedPreferenceUserInt(NUMBER_HARD_WINNINGS, number_hard_winnings);
    }

    public void setIs_adv(boolean is_adv) {
        this.is_adv = is_adv;
        changeSharedPreferenceUserBoolean(IS_ADV, is_adv);
    }

    public void setIs_books(boolean is_books) {
        this.is_books = is_books;
        changeSharedPreferenceUserBoolean(IS_BOOKS, is_books);
    }

    public void setIs_films(boolean is_films) {
        this.is_films = is_films;
        changeSharedPreferenceUserBoolean(IS_FILMS, is_films);
    }

    public void setCurrent_theme(int current_theme) {
        this.current_theme = current_theme;
        changeSharedPreferenceUserInt(CURRENT_THEME, current_theme);
    }

    public void setIs_skin_targar(boolean is_skin_targar) {
        this.is_skin_targar = is_skin_targar;
        changeSharedPreferenceUserBoolean(IS_SKIN_TARGAR, is_skin_targar);
    }

    public void setIs_skin_lann(boolean is_skin_lann) {
        this.is_skin_lann = is_skin_lann;
        changeSharedPreferenceUserBoolean(IS_SKIN_LANN, is_skin_lann);
    }

    public void setIs_skin_stark(boolean is_skin_stark) {
        this.is_skin_stark = is_skin_stark;
        changeSharedPreferenceUserBoolean(IS_SKIN_STARK, is_skin_stark);
    }

    public void setIs_skin_night(boolean is_skin_night) {
        this.is_skin_night = is_skin_night;
        changeSharedPreferenceUserBoolean(IS_SKIN_NIGHT, is_skin_night);
    }

    public void setIs_credit(boolean is_credit) {
        this.is_credit = is_credit;
        changeSharedPreferenceUserBoolean(IS_CREDIT, is_credit);
    }

    public void setCredit_time(long credit_time) {
        this.credit_time = credit_time;
        changeSharedPreferenceUserLong(CREDIT_TIME, credit_time);
    }

    public void addCredit_sum(long adding_credit) {
        this.credit_sum += adding_credit;
        changeSharedPreferenceUserLong(CREDIT_SUM, credit_sum);
    }

    public void setCredit_sum(long credit_sum) {
        this.credit_sum = credit_sum;
        changeSharedPreferenceUserLong(CREDIT_SUM, credit_sum);
    }

    public void setIs_debit(boolean is_debit) {
        this.is_debit = is_debit;
        changeSharedPreferenceUserBoolean(IS_DEBIT, is_debit);
    }


    public void setDebit_time(long debit_time) {
        this.debit_time = debit_time;
        changeSharedPreferenceUserLong(DEBIT_TIME, debit_time);
    }

    public void addDebit_sum(long adding_debit) {
        this.debit_sum += adding_debit;
        changeSharedPreferenceUserLong(DEBIT_SUM, debit_sum);
    }

    public void setDebit_sum(long debit_sum) {
        this.debit_sum = debit_sum;
        changeSharedPreferenceUserLong(DEBIT_SUM, debit_sum);
    }

    private void changeSharedPreferenceUserInt(String WHAT_TO_CHANGE, int value) {
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putInt(WHAT_TO_CHANGE, value);
        editor.apply();
    }

    private void changeSharedPreferenceUserLong(String WHAT_TO_CHANGE, long value) {
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putLong(WHAT_TO_CHANGE, value);
        editor.apply();
    }

    private void changeSharedPreferenceUserString(String WHAT_TO_CHANGE, String value) {
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putString(WHAT_TO_CHANGE, value);
        editor.apply();
    }

    private void changeSharedPreferenceUserBoolean(String WHAT_TO_CHANGE, boolean value) {
        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
        editor.putBoolean(WHAT_TO_CHANGE, value);
        editor.apply();
    }



}

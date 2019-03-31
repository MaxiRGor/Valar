package harelchuk.maxim.quizwithmoxy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class User {
    @SerializedName("id_user")
    @Expose
    private Integer id_user;

    @SerializedName("user_uuid")
    @Expose
    private String user_uuid;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("user_money")
    @Expose
    private long user_money;

    @SerializedName("easy_games")
    @Expose
    private int easy_games;

    @SerializedName("medium_games")
    @Expose
    private int medium_games;

    @SerializedName("hard_games")
    @Expose
    private int hard_games;

    @SerializedName("easy_winnings")
    @Expose
    private int easy_winnings;

    @SerializedName("medium_winnings")
    @Expose
    private int medium_winnings;

    @SerializedName("hard_winnings")
    @Expose
    private int hard_winnings;

    @SerializedName("is_adv")
    @Expose
    private boolean is_adv;

    @SerializedName("is_books")
    @Expose
    private boolean is_books;

    @SerializedName("is_films")
    @Expose
    private boolean is_films;

    @SerializedName("is_skin_targar")
    @Expose
    private boolean is_skin_targar;

    @SerializedName("is_skin_stark")
    @Expose
    private boolean is_skin_stark;

    @SerializedName("is_skin_lann")
    @Expose
    private boolean is_skin_lann;

    @SerializedName("is_skin_night")
    @Expose
    private boolean is_skin_night;

    @SerializedName("current_theme")
    @Expose
    private int current_theme;

    @SerializedName("is_credit")
    @Expose
    private boolean is_credit;

    @SerializedName("credit_time")
    @Expose
    private long credit_time;

    @SerializedName("credit_time_to_increase")
    @Expose
    private long credit_time_to_increase;

    @SerializedName("credit_sum")
    @Expose
    private long credit_sum;

    @SerializedName("is_debit")
    @Expose
    private boolean is_debit;

    @SerializedName("debit_time")
    @Expose
    private long debit_time;

    @SerializedName("debit_sum")
    @Expose
    private long debit_sum;

    Integer getId_user() {
        return id_user;
    }

    String getUser_uuid() {
        return user_uuid;
    }

    String getUser_name() {
        return user_name;
    }

    long getUser_money() {
        return user_money;
    }

    int getEasy_games() {
        return easy_games;
    }

    int getMedium_games() {
        return medium_games;
    }

    int getHard_games() {
        return hard_games;
    }

    int getEasy_winnings() {
        return easy_winnings;
    }

    int getMedium_winnings() {
        return medium_winnings;
    }

    int getHard_winnings() {
        return hard_winnings;
    }

    boolean isIs_adv() {
        return is_adv;
    }

    boolean isIs_books() {
        return is_books;
    }

    boolean isIs_films() {
        return is_films;
    }

    boolean isIs_skin_targar() {
        return is_skin_targar;
    }

    boolean isIs_skin_stark() {
        return is_skin_stark;
    }

    boolean isIs_skin_lann() {
        return is_skin_lann;
    }

    boolean isIs_skin_night() {
        return is_skin_night;
    }

    int getCurrent_theme() {
        return current_theme;
    }

    boolean isIs_credit() {
        return is_credit;
    }

    long getCredit_time_to_increase() {
        return credit_time_to_increase;
    }

    long getCredit_time() {
        return credit_time;
    }

    long getCredit_sum() {
        return credit_sum;
    }

    boolean isIs_debit() {
        return is_debit;
    }

    long getDebit_time() {
        return debit_time;
    }

    long getDebit_sum() {
        return debit_sum;
    }
}

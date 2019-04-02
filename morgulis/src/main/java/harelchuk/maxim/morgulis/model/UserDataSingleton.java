package harelchuk.maxim.morgulis.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserDataSingleton {

    //private final int MY_USER_ID = 1;

    private boolean isConnected;

    private int user_id;

    private String user_uuid;
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
    private long credit_time_to_increase;
    private long credit_sum;

    private boolean is_debit;
    private long debit_time;
    private long debit_sum;

    private int chosen_level;

    private static final UserDataSingleton oneInstance = new UserDataSingleton();

    public static UserDataSingleton getInstance() {
        return oneInstance;
    }

    private UserDataSingleton() {
        this.isConnected = false;
        //getUserById();
        getUUIDFromSharedPreferences();
    }

    private void getUUIDFromSharedPreferences() {
        SharedPreferences sp_uuid = AppForContext.getContext().getSharedPreferences("SP_UUID", Context.MODE_PRIVATE);
        boolean isSP = sp_uuid.getBoolean("is_SP", false);
        if (!isSP) {
            String stringUUID = UUID.randomUUID().toString();
            this.user_uuid = stringUUID;
            SharedPreferences.Editor editor = sp_uuid.edit();
            editor.putBoolean("is_SP", true);
            editor.putString("STRING_UUID", stringUUID);
            editor.apply();
            Log.d("myLogs", "SP CREATED + " + stringUUID);
            createUserByUUIdOnServer(stringUUID);
        }
        if (isSP) {
            String stringUUID = sp_uuid.getString("STRING_UUID", "FAIL");
            Log.d("myLogs", "SP READ + " + stringUUID);
            //this.user_uuid = stringUUID;
            getUserByUUID(stringUUID);
        }
    }

    private void createUserByUUIdOnServer(final String stringUUID) {
        Log.d("myLogs", "TRYING TO CREATE USER BY UUID");
        NetworkService.getInstance().getJSONApi().createUserByUUID(stringUUID).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                Log.d("myLogs", "USER CREATED");
                User user = response.body();
                if (response.isSuccessful()) {
                    assert user != null;
                    getVariables(user);
                } else {
                    Log.d("myLogs", "-------------RESPONSE USER CREATED IS NOT SUCCESSFUL");
                    removeInfoFromSP();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                isConnected = false;
                removeInfoFromSP();
            }
        });
    }


    private void getUserByUUID(String stringUUID) {
        Log.d("myLogs", "TRYING TO FIND USER BY UUID");
        NetworkService.getInstance().getJSONApi().getUserByUUID(stringUUID).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                Log.d("myLogs", "USER GOT");
                User user = response.body();
                if (response.isSuccessful()) {
                    assert user != null;
                    getVariables(user);
                } else {
                    Log.d("myLogs", "-------------RESPONSE USER GOT IS NOT SUCCESSFUL");
                }

            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                isConnected = false;
            }
        });
    }

    private void removeInfoFromSP() {
        SharedPreferences sharedPreferences = AppForContext.getContext().getSharedPreferences("SP_UUID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_SP", false);
        editor.apply();
    }

    private void getVariables(User user) {

        Log.d("myLogs", "GETTING VARIABLES GOT");

        this.user_id = user.getId_user();
        this.user_uuid = user.getUser_uuid();
        this.user_name = user.getUser_name();

        this.user_money = user.getUser_money();

        this.number_easy_games = user.getEasy_games();
        this.number_medium_games = user.getMedium_games();
        this.number_hard_games = user.getHard_games();

        this.number_easy_winnings = user.getEasy_winnings();
        this.number_medium_winnings = user.getMedium_winnings();
        this.number_hard_winnings = user.getHard_winnings();

        this.is_adv = user.isIs_adv();

        this.is_books = user.isIs_books();
        this.is_films = user.isIs_films();

        this.current_theme = user.getCurrent_theme();
        this.is_skin_targar = user.isIs_skin_targar();
        this.is_skin_stark = user.isIs_skin_stark();
        this.is_skin_lann = user.isIs_skin_lann();
        this.is_skin_night = user.isIs_skin_night();

        this.is_credit = user.isIs_credit();
        this.credit_time = user.getCredit_time();
        this.credit_time_to_increase = user.getCredit_time_to_increase();
        this.credit_sum = user.getCredit_sum();

        this.is_debit = user.isIs_debit();
        this.debit_time = user.getDebit_time();
        this.debit_sum = user.getDebit_sum();

        this.chosen_level = 0;
        this.isConnected = true;
        Log.d("myLogs", "VARIABLES GOT");
    }

    private void executeVoid(Call<Void> call) {
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful())
                    Log.d("myLogs",
                            "USER INFO UPDATED ");
                else
                    Log.d("myLogs",
                            "-----------------------------------------------------------------------USER INFO NOT UPDATED");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d("myLogs",
                        "--------------------------------------Throwable---------------------------------USER INFO NOT UPDATED");
            }
        });
    }

    //getters

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

    public long getCredit_time_to_increase() {
        return credit_time_to_increase;
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

    //setters

    public void setChosen_level(int chosen_level) {
        this.chosen_level = chosen_level;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setUser_name(this.user_id, user_name);
        executeVoid(call);
    }

    /*
        public void setUser_uuid() {
            if (this.user_uuid.equals("EMPTY")) {
                String uuid = UUID.randomUUID().toString();
                this.user_uuid = uuid;
                Call<Void> call = NetworkService.getInstance().getJSONApi().setUser_uuid(this.user_id, uuid);
                executeVoid(call);
            }
        }
    */
    public void addUserMoney(long add) {
        this.user_money += add;
        Call<Void> call = NetworkService.getInstance().getJSONApi().addUser_money(this.user_id, add);
        executeVoid(call);
    }

    public void subtractUserMoney(long subtract) {
        this.user_money -= subtract;
        Call<Void> call = NetworkService.getInstance().getJSONApi().subtractUser_money(this.user_id, subtract);
        executeVoid(call);
    }

    public void updateEasyWin() {
        this.number_easy_winnings++;
        this.number_easy_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().easy_win(this.user_id);
        executeVoid(call);
    }

    public void updateEasyLose() {
        this.number_easy_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().easy_lose(this.user_id);
        executeVoid(call);
    }

    public void updateMediumWin() {
        this.number_medium_games++;
        this.number_medium_winnings++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().medium_win(this.user_id);
        executeVoid(call);
    }

    public void updateMediumLose() {
        this.number_medium_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().medium_lose(this.user_id);
        executeVoid(call);
    }

    public void updateHardWin() {
        this.number_hard_games++;
        this.number_hard_winnings++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().hard_win(this.user_id);
        executeVoid(call);
    }

    public void updateHardLose() {
        this.number_hard_games++;
        Call<Void> call = NetworkService.getInstance().getJSONApi().hard_lose(this.user_id);
        executeVoid(call);
    }

    public void setIs_adv(boolean is_adv) {
        this.is_adv = is_adv;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_adv(this.user_id, is_adv);
        executeVoid(call);
    }

    public void setIs_books(boolean is_books) {
        this.is_books = is_books;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_books(this.user_id, is_books);
        executeVoid(call);
    }

    public void setIs_films(boolean is_films) {
        this.is_films = is_films;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_films(this.user_id, is_films);
        executeVoid(call);
    }

    public void setIs_skin_targar(boolean is_skin_targar) {
        this.is_skin_targar = is_skin_targar;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_skin_targar(this.user_id, is_skin_targar);
        executeVoid(call);
    }

    public void setIs_skin_lann(boolean is_skin_lann) {
        this.is_skin_lann = is_skin_lann;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_skin_lann(this.user_id, is_skin_lann);
        executeVoid(call);
    }

    public void setIs_skin_stark(boolean is_skin_stark) {
        this.is_skin_stark = is_skin_stark;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_skin_stark(this.user_id, is_skin_stark);
        executeVoid(call);
    }

    public void setIs_skin_night(boolean is_skin_night) {
        this.is_skin_night = is_skin_night;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setIs_skin_night(this.user_id, is_skin_night);
        executeVoid(call);
    }

    public void setCurrent_theme(int current_theme) {
        this.current_theme = current_theme;
        Call<Void> call = NetworkService.getInstance().getJSONApi().setCurrent_theme(this.user_id, current_theme);
        executeVoid(call);
    }

    public void getCredit(long add) {
        long current_time = new Date().getTime();
        this.user_money += add;
        this.is_credit = true;
        this.credit_sum = add;
        this.credit_time = current_time;
        this.credit_time_to_increase = current_time;
        Call<Void> call = NetworkService.getInstance().getJSONApi().get_credit(this.user_id, add);
        executeVoid(call);
    }

    public void removeCredit() {
        long money = this.getUser_money();
        long credit_sum = updated_credit_sum_and_time()[0];
        if (money > credit_sum) {
            this.user_money = (money - credit_sum);
            this.is_credit = false;
            this.credit_sum = 0;
            this.credit_time = 0;
            this.credit_time_to_increase = 0;
            Call<Void> call = NetworkService.getInstance().getJSONApi().remove_credit(this.user_id);
            executeVoid(call);
        }
    }

    public long[] updated_credit_sum_and_time() {
        if (is_credit) {
            long current_time = new Date().getTime();
            long[] credit_sum_and_time = new long[2];
            credit_sum_and_time[0] = this.getCredit_sum();
            credit_sum_and_time[1] = this.getCredit_time_to_increase();
            if ((current_time - credit_sum_and_time[1]) > (21600000)) {
                while ((current_time - credit_sum_and_time[1]) > (21600000)) {
                    credit_sum_and_time[0] *= 1.07;
                    credit_sum_and_time[1] += 21600000;
                }
            }
            this.credit_sum = credit_sum_and_time[0];
            this.credit_time_to_increase = credit_sum_and_time[1];
            return credit_sum_and_time;
        }
        return new long[]{0, 0};
    }

    public void add_debit(long add) {
        long current_time = new Date().getTime();
        this.user_money -= add;
        this.is_debit = true;
        this.debit_sum = updated_debit_sum_and_time()[0];
        this.debit_sum += add;
        this.debit_time = current_time;
        Call<Void> call = NetworkService.getInstance().getJSONApi().add_debit(this.user_id, add);
        executeVoid(call);
    }

    public void remove_debit() {
        long money = this.user_money;
        long debit_sum = updated_debit_sum_and_time()[0];
        this.user_money = money + debit_sum;
        this.is_debit = false;
        this.debit_sum = 0;
        this.debit_time = 0;
        Call<Void> call = NetworkService.getInstance().getJSONApi().remove_debit(this.user_id);
        executeVoid(call);
    }

    public long[] updated_debit_sum_and_time() {
        if (is_debit) {
            long current_time = new Date().getTime();
            long sum, time;
            sum = this.debit_sum;
            time = this.debit_time;
            if ((current_time - time) > (21600000)) {
                while ((current_time - time) > (21600000)) {
                    sum *= 1.06;
                    time += 21600000;
                }
            }
            this.debit_sum = sum;
            this.debit_time = time;
            return new long[]{sum, time};
        }
        return new long[]{0, 0};
    }

    public boolean isConnected() {
        return isConnected;
    }
}

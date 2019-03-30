package harelchuk.maxim.quizwithmoxy.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/ru/level/{level}/{in_book}/{in_serial}")
    Call<List<Question>> getByLevel(@Path("level") int level,
                                    @Path("in_book") boolean in_book,
                                    @Path("in_serial") boolean in_serial);
/*
    @GET("/ru/category/{category}/{in_book}/{in_serial}")
    Call<List<Question>> getByCategory(@Path("category") int category,
                                       @Path("in_book") boolean in_book,
                                       @Path("in_serial") boolean in_serial);
*/
    @GET("/users/{id_user}")
    Call<User> getUserInfo (@Path("id_user") int id_user);

    @PUT("/answer/{id_answer}/{user_answer}")
    Call<Void> updateAnswer(@Path("id_answer") int id_answer, @Path("user_answer") int user_answer);

    @PUT("/update/user/{id_user}/user_uuid/{user_uuid}")
    Call<Void> updateUser_uuid(@Path("id_user") int id_user, @Path("user_uuid") long user_uuid);

    @PUT("/update/user/{id_user}/user_name/{user_name}")
    Call<Void> updateUser_name(@Path("id_user") int id_user, @Path("user_name") String user_name);

    @PUT("/update/user/{id_user}/user_money/{user_money}")
    Call<Void> updateUser_money(@Path("id_user") int id_user, @Path("user_money") long user_money);

    @PUT("/update/user/{id_user}/easy_games/{easy_games}")
    Call<Void> updateEasy_games(@Path("id_user") int id_user, @Path("easy_games") int easy_games);

    @PUT("/update/user/{id_user}/medium_games/{medium_games}")
    Call<Void> updateMedium_games(@Path("id_user") int id_user, @Path("medium_games") int medium_games);

    @PUT("/update/user/{id_user}/hard_games/{hard_games}")
    Call<Void> updateHard_games(@Path("id_user") int id_user, @Path("hard_games") int hard_games);

    @PUT("/update/user/{id_user}/easy_winnings/{easy_winnings}")
    Call<Void> updateEasy_winnings(@Path("id_user") int id_user, @Path("easy_winnings") int easy_winnings);

    @PUT("/update/user/{id_user}/medium_winnings/{medium_winnings}")
    Call<Void> updateMedium_winnings(@Path("id_user") int id_user, @Path("medium_winnings") int medium_winnings);

    @PUT("/update/user/{id_user}/hard_winnings/{hard_winnings}")
    Call<Void> updateHard_winnings(@Path("id_user") int id_user, @Path("hard_winnings") int hard_winnings);

    @PUT("/update/user/{id_user}/is_adv/{is_adv}")
    Call<Void> updateIs_adv(@Path("id_user") int id_user, @Path("is_adv") boolean is_adv);

    @PUT("/update/user/{id_user}/is_books/{is_books}")
    Call<Void> updateIs_books(@Path("id_user") int id_user, @Path("is_books") boolean is_books);

    @PUT("/update/user/{id_user}/is_films/{is_films}")
    Call<Void> updateIs_films(@Path("id_user") int id_user, @Path("is_films") boolean is_films);

    @PUT("/update/user/{id_user}/is_skin_targar/{is_skin_targar}")
    Call<Void> updateIs_skin_targar(@Path("id_user") int id_user, @Path("is_skin_targar") boolean is_skin_targar);

    @PUT("/update/user/{id_user}/is_skin_stark/{is_skin_stark}")
    Call<Void> updateIs_skin_stark(@Path("id_user") int id_user, @Path("is_skin_stark") boolean is_skin_stark);

    @PUT("/update/user/{id_user}/is_skin_lann/{is_skin_lann}")
    Call<Void> updateIs_skin_lann(@Path("id_user") int id_user, @Path("is_skin_lann") boolean is_skin_lann);

    @PUT("/update/user/{id_user}/is_skin_night/{is_skin_night}")
    Call<Void> updateIs_skin_night(@Path("id_user") int id_user, @Path("is_skin_night") boolean is_skin_night);

    @PUT("/update/user/{id_user}/current_theme/{current_theme}")
    Call<Void> updateCurrent_theme(@Path("id_user") int id_user, @Path("current_theme") int current_theme);

    @PUT("/update/user/{id_user}/is_credit/{is_credit}")
    Call<Void> updateIs_credit(@Path("id_user") int id_user, @Path("is_credit") boolean is_credit);

    @PUT("/update/user/{id_user}/credit_time/{credit_time}")
    Call<Void> updateCredit_time(@Path("id_user") int id_user, @Path("credit_time") long credit_time);

    @PUT("/update/user/{id_user}/credit_sum/{credit_sum}")
    Call<Void> updateCredit_sum(@Path("id_user") int id_user, @Path("credit_sum") long credit_sum);

    @PUT("/update/user/{id_user}/is_debit/{is_debit}")
    Call<Void> updateIs_debit(@Path("id_user") int id_user, @Path("is_debit") boolean is_debit);

    @PUT("/update/user/{id_user}/debit_time/{debit_time}")
    Call<Void> updateDebit_time(@Path("id_user") int id_user, @Path("debit_time") long debit_time);

    @PUT("/update/user/{id_user}/debit_sum/{debit_sum}")
    Call<Void> updateDebit_sum(@Path("id_user") int id_user, @Path("debit_sum") long debit_sum);

}

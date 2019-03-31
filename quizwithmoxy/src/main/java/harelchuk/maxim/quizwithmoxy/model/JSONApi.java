package harelchuk.maxim.quizwithmoxy.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONApi {
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

    @PUT("/user_answer/{id_answer}/{user_answer}")
    Call<Void> updateAnswer(@Path("id_answer") int id_answer, @Path("user_answer") int user_answer);

    @GET("/users/create/{user_uuid}")
    Call<User> createUserByUUID(@Path("user_uuid") String user_uuid);

    @GET("/users/get/{user_uuid}")
    Call<User> getUserByUUID(@Path("user_uuid") String user_uuid);

    /*
    @GET("/users/{id_user}")
    Call<User> getUserById(@Path("id_user") int id_user);
*/


    /*
    @PUT("/update/user/{id_user}/user_uuid/{user_uuid}")
    Call<Void> setUser_uuid(@Path("id_user") int id_user, @Path("user_uuid") String user_uuid);
*/
    @PUT("/update/user/{id_user}/user_name/{user_name}")
    Call<Void> setUser_name(@Path("id_user") int id_user, @Path("user_name") String user_name);

    @PUT("/update/user/{id_user}/add_user_money/{sum}")
    Call<Void> addUser_money(@Path("id_user") int id_user, @Path("sum") long add);

    @PUT("/update/user/{id_user}/subtract_user_money/{subtract}")
    Call<Void> subtractUser_money(@Path("id_user") int id_user, @Path("subtract") long subtract);

    @PUT("/update/user/{id_user}/easy_lose")
    Call<Void> easy_lose(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/easy_win")
    Call<Void> easy_win(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/medium_lose")
    Call<Void> medium_lose(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/medium_win")
    Call<Void> medium_win(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/hard_lose")
    Call<Void> hard_lose(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/hard_win")
    Call<Void> hard_win(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/is_adv/{is_adv}")
    Call<Void> setIs_adv(@Path("id_user") int id_user, @Path("is_adv") boolean is_adv);

    @PUT("/update/user/{id_user}/is_books/{is_books}")
    Call<Void> setIs_books(@Path("id_user") int id_user, @Path("is_books") boolean is_books);

    @PUT("/update/user/{id_user}/is_films/{is_films}")
    Call<Void> setIs_films(@Path("id_user") int id_user, @Path("is_films") boolean is_films);

    @PUT("/update/user/{id_user}/is_skin_targar/{is_skin_targar}")
    Call<Void> setIs_skin_targar(@Path("id_user") int id_user, @Path("is_skin_targar") boolean is_skin_targar);

    @PUT("/update/user/{id_user}/is_skin_stark/{is_skin_stark}")
    Call<Void> setIs_skin_stark(@Path("id_user") int id_user, @Path("is_skin_stark") boolean is_skin_stark);

    @PUT("/update/user/{id_user}/is_skin_lann/{is_skin_lann}")
    Call<Void> setIs_skin_lann(@Path("id_user") int id_user, @Path("is_skin_lann") boolean is_skin_lann);

    @PUT("/update/user/{id_user}/is_skin_night/{is_skin_night}")
    Call<Void> setIs_skin_night(@Path("id_user") int id_user, @Path("is_skin_night") boolean is_skin_night);

    @PUT("/update/user/{id_user}/current_theme/{current_theme}")
    Call<Void> setCurrent_theme(@Path("id_user") int id_user, @Path("current_theme") int current_theme);

    @PUT("/update/user/{id_user}/add_credit/{add}")
    Call<Void> get_credit(@Path("id_user") int id_user, @Path("add") long add);

    @PUT("/update/user/{id_user}/remove_credit")
    Call<Void> remove_credit(@Path("id_user") int id_user);

    @GET("/update/user/{id_user}/updated_credit_sum_and_time")
    Call<long[]> updated_credit_sum_and_time(@Path("id_user") int id_user);

    @PUT("/update/user/{id_user}/add_debit/{add}")
    Call<Void> add_debit(@Path("id_user") int id_user, @Path("add") long add);

    @PUT("/update/user/{id_user}/remove_debit")
    Call<Void> remove_debit(@Path("id_user") int id_user);

    @GET("/update/user/{id_user}/updated_debit_sum_and_time")
    Call<long[]> updated_debit_sum_and_time(@Path("id_user") int id_user);

}

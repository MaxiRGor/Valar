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

    @GET("/ru/category/{category}/{in_book}/{in_serial}")
    Call<List<Question>> getByCategory(@Path("category") int category,
                                       @Path("in_book") boolean in_book,
                                       @Path("in_serial") boolean in_serial);

    @PUT("/answer/{id_answer}/{user_answer}")
    Call<Void> updateAnswer(@Path("id_answer") int id_answer, @Path("user_answer") int user_answer);
}

package harelchuk.maxim.quizwithmoxy.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/level/{level}")
    Call<List<Question>> getByLevel(@Path("level") int level);

    @GET("/category/{category}")
    Call<List<Question>> getByCategory(@Path("category") int category);

    @PUT("/answer/{id_answer}/{user_answer}")
    Call<Void> updateAnswer(@Path("id_answer") int id_answer, @Path("user_answer") int user_answer);
}

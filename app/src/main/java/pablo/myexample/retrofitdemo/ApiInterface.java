package pablo.myexample.retrofitdemo;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("posts/{num}/comments")
    Call<List<Contact>> listRepos(@Path("num") String num);
}

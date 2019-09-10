package pablo.myexample.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    Call<List<Contact>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Convert to gson in order to be able to use getter methods
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //get instance of interface to use get method
        ApiInterface service = retrofit.create(ApiInterface.class);
        //store the call after passing parameter
        call = service.listRepos("1");

    }

    public void Go(View view) {
        //call the url
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                //returns a response object with a list body
                List<Contact> contacts = response.body();
                //loop through list and get the names into the logcat
                for (int i = 0; i < 20; i++) {
                    Log.i(String.valueOf(i), contacts.get(i).getName());
                }
            }
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }
}

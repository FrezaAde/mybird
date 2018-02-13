package retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by canc on 23/08/2017.
 */

public class APIClient {
    public static final String BASE_URL = "http://mybird.silobur.com/components/json/";
    private static Retrofit retrofit = null;
    // variable to hold context
    private static Context context;

    public static Retrofit getClient() {
        if (retrofit==null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }



//    public static Retrofit getdata() {
//        if (retrofit==null) {
//
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }
//    private static Retrofit getRetrofit(){
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL1)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        return retrofit;
//    }


    public static APIService getInstanceRetrofit(){
        return getClient().create(APIService.class);
    }

}

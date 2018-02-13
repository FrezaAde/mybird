package retrofit;

import com.mybird.SubCatModel;

import java.util.ArrayList;

import model.BrosurModel;
import model.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by canc on 23/08/2017.
 */

public interface APIService {

    @GET("get_burungNews.php")
    Call<ArrayList<NewsModel>> getnews();
//    Call<NewsList> championsRequest();
//
//    @GET("apiproducts.php")
//    Call<ArrayList<productsModel>> ambilDataProduct();
//    // langkah ke 3 selanjutnya ke aktifiti la
//    @GET("apicart.php")
//    Call<ArrayList<cartModel>> ambilDataCart(); // ambilDataCart di arahkan ke cartActifity

    @GET("get_burungSubCategory.php")
    Call<ArrayList<SubCatModel>> ambilData();

    @GET("get_brosur.php")
    Call<ArrayList<BrosurModel>> ambilBrosur();





}

package id.ac.budiluhur.qrbeta3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Muhammad Saddam Nur on 5/6/2017.
 */

public interface ApiService3 {
    @FormUrlEncoded
    @POST("/DisplayJsonQRlogin.php")
    Call<ListQRModel> search(@Field("search") String search);


}
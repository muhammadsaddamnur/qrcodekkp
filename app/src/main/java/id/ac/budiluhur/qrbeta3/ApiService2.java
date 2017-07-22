package id.ac.budiluhur.qrbeta3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by saddamnur on 4/27/2017.
 */

public interface ApiService2 {
    @FormUrlEncoded
    @POST("/DisplayJsonQRImei.php")
    Call<ListQRModel> search(@Field("search") String search);
}

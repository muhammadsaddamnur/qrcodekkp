package id.ac.budiluhur.qrbeta3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



/**
 * Created by saddamnur on 5/7/2017.
 */

public interface ApiService4 {
    @FormUrlEncoded
    @POST("/DisplayJsonQRcam.php")
    Call<ListQRModel> search(@Field("search") String search);
}

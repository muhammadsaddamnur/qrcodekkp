package id.ac.budiluhur.qrbeta3;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by saddamnur on 5/26/2017.
 */

public interface ApiService6 {
    @FormUrlEncoded
    @POST("/DisplayJsonQRUser.php")
    Call<ListUserModel> search(@Field("search") String search);

}

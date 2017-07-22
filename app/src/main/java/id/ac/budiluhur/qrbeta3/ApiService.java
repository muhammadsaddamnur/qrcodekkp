package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 4/21/2017.
 */


import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiService {
    @FormUrlEncoded
    @POST("/InsertQR.php")
    Call<InsertQRResponseModel> insertQR(@Field("imei") String imei, @Field("date") String date,@Field("signin") String signin, @Field("signout") String signout, @Field("total") String total);


}


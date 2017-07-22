package id.ac.budiluhur.qrbeta3;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by saddamnur on 5/26/2017.
 */

public interface ApiService5 {
    @FormUrlEncoded
    @POST("/InsertQRregis.php")
    Call<InsertQRResponseModel> insertregisQR(@Field("imei") String imei, @Field("nama") String nama, @Field("email") String email, @Field("notelepon") String notelepon, @Field("noktp") String noktp, @Field("tgllahir") String tgllahir, @Field("alamat") String alamat);

}

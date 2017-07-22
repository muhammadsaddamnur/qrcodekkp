package id.ac.budiluhur.qrbeta3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class login extends AppCompatActivity {

    //Set waktu lama login
    private static int splashInterval = 2000;
    private static final int REQUEST_READ_PHONE_STATE = 0; //variabel request read phone status/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        /*meminta permission*/
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) { /*jika tidak ada permisson sebelumnya maka buat permission*/
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
            } else { /*jika ada maka perintah selanjutnya*/


            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.login);


            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    LoadData();


                    //jeda selesai login
                    this.finish();
                }

                private void finish() {
                    // TODO Auto-generated method stub

                }
            }, splashInterval);
        }
    };


    private void LoadData() {
        /*mengambil imei dan melakukan hashing =>*/
        TelephonyManager tel;
        tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String teli0 = tel.getDeviceId();
        md5 md5 = new md5();
        String teli = md5.rmd5(teli0);
        /*<=mengambil imei dan melakukan hashing*/

        ApiService3 apiService = ApiClient.getClient().create(ApiService3.class);
        Call<ListQRModel> call = apiService.search(teli); //melempar variabel teli ke server untuk melakukan query
        call.enqueue(new Callback<ListQRModel>() {
            @Override
            public void onResponse(Call<ListQRModel> call, Response<ListQRModel> response) {
                ListQRModel listQrModel = response.body();
                if(listQrModel.getStatus()==1){ //jika statusnya 1 (berhasil)
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i); // menghubungkan activity login ke main activity dengan intent
                }else{

                    Intent u = new Intent(login.this, register.class);
                    startActivity(u); // menghubungkan activity splashscren ke main activity dengan intent
                }
            }

            @Override
            public void onFailure(Call<ListQRModel> call, Throwable t) {
                Toast.makeText(login.this, t.getMessage(), Toast.LENGTH_SHORT).show(); //jika error maka muncul pesan

            }
        });
    }
}
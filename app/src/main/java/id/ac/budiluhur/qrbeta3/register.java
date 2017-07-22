package id.ac.budiluhur.qrbeta3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muhammad Saddam Nur on 5/1/2017.
 */

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        final Button button = (Button) findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String key = "MINDREACH";
                TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String teli = tel.getDeviceId().toString();
                String imei = md5.rmd5(teli);

                TextView txtnama = (TextView) findViewById(R.id.input_name);
                TextView txtemail = (TextView) findViewById(R.id.input_email);
                TextView txtnotelp = (TextView) findViewById(R.id.txtnotelp);
                TextView txtnoktp = (TextView) findViewById(R.id.txtnoktp);
                TextView txttgllahir = (TextView) findViewById(R.id.txttgllahir);
                TextView txtalamat = (TextView) findViewById(R.id.txtalamat);


                String namaplan2 = new String(txtnama.getText().toString());
                String emailplan2 = new String(txtemail.getText().toString());
                String notelp = new String(txtnotelp.getText().toString());
                String noktp2 = new String(txtnoktp.getText().toString());
                String tgllahir2 = new String(txttgllahir.getText().toString());
                String alamat2 = new String(txtalamat.getText().toString());


                VigenereCipher VigenereCipher = new VigenereCipher();

                String nama = VigenereCipher.vigenere_cipher(namaplan2, key, true);
                String email = VigenereCipher.vigenere_cipher(emailplan2, key, true);
                String notelepon = VigenereCipher.vigenere_cipher(notelp, key, true);
                String noktp = VigenereCipher.vigenere_cipher(noktp2, key, true);
                String tgllahir = VigenereCipher.vigenere_cipher(tgllahir2, key, true);
                String alamat = VigenereCipher.vigenere_cipher(alamat2, key, true);

                if(!isValidateEmail(emailplan2)){
                    Toast.makeText(register.this,"Email Salah", Toast.LENGTH_SHORT).show();
                }else if(!isEmptyField(namaplan2)){
                    Toast.makeText(register.this,"Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(!isEmptyField(notelepon)){
                    Toast.makeText(register.this,"No Telepon tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(!isEmptyField(noktp2)){
                    Toast.makeText(register.this,"No ktp tidak bokeh kosong", Toast.LENGTH_SHORT).show();
                }else if(!isEmptyField(tgllahir2)){
                    Toast.makeText(register.this,"Tanggal Lahir tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(!isEmptyField(alamat2)) {
                    Toast.makeText(register.this,"Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    insertData(imei, nama, email, notelepon, noktp, tgllahir, alamat);
                }
            }
        });
    }



    /**
     *
     * @param email
     * Method dibawah ini untuk validasi email kosong atau salah
     */
    private boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     *
     * @param yourField
     * Method ini digunakan untuk validasi field kosong atau tidak
     */
    private boolean isEmptyField(String yourField){
        return !TextUtils.isEmpty(yourField);
    }
















    private void insertData(String imei, String nama, String email, String notelepon, String noktp, String tgllshir, String alamat){
        ApiService5 apiService = ApiClient.getClient().create(ApiService5.class);
        Call<InsertQRResponseModel> call = apiService.insertregisQR(imei, nama, email, notelepon, noktp, tgllshir, alamat);
        call.enqueue(new Callback<InsertQRResponseModel>() {
            @Override
            public void onResponse(Call<InsertQRResponseModel> call, Response<InsertQRResponseModel> response) {

                InsertQRResponseModel InsertQRResponseModel = response.body();

                //check the status code
                if(InsertQRResponseModel.getStatus()==1){
                    Toast.makeText(register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(register.this, MainActivity.class);
                    startActivity(i); // menghubungkan activity splashscren ke main activity dengan intent
                }else{
                    Toast.makeText(register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertQRResponseModel> call, Throwable t) {
                Toast.makeText(register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

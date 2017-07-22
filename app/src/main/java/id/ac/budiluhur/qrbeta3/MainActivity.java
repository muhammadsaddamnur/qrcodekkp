package id.ac.budiluhur.qrbeta3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//library untuk kamera
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private static final int REQUEST_READ_PHONE_STATE = 0; //variabel request read phone status/
    TelephonyManager tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




        //mengecek permission untuk mengakses read phone states (mengambil kode imei)
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            setDeviceImei();

        }
        //mengecek permission untuk mengakses read phone states (mengambil kode imei)



        final Activity activity = this;
        //action float untuk scan
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES); //jenisnya QR code
                integrator.setPrompt("Arahkan Kamera pada QR Code untuk melakukan absensi");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });






        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    //meminta hasil request untuk mengambil kode imei
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    setDeviceImei();
                }
                break;
            default:
                break;
        }
    }
    //meminta hasil request untuk mengambil kode imei


    private void setDeviceImei() {
        //fungsi untuk menampilkan kode imei
        tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String teli = tel.getDeviceId();
        TextView txtimei = (TextView) findViewById(R.id.txtimei);
        txtimei.setText(teli);
        //fungsi untuk menampilkan kode imei
    }



    //fungsi untuk memproses qr code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){ //jika hasil qr tidak kosong

            if(result.getContents()==null){
                //jika keluar dari kamera
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
            }else {//jika tidak keluar dari kamera dan melakukan scaning
                String content = result.getContents(); //mengambil hasil scan ke variabel content

                /*cek qr retrofit*/
                ApiService4 apiService = ApiClient.getClient().create(ApiService4.class);
                Call<ListQRModel> call = apiService.search(content); //melempar variabel konten ke server untuk melakukan query
                call.enqueue(new Callback<ListQRModel>() {
                    @Override
                    public void onResponse(Call<ListQRModel> call, Response<ListQRModel> response) {
                        ListQRModel listQrModel = response.body();
                        if(listQrModel.getStatus()==1){ //jika statusnya 1(berhasil) maka

                            TextView txtmasuk = (TextView) findViewById(R.id.txtmasuk);
                            TextView txtkeluar = (TextView) findViewById(R.id.txtkeluar);

                            switch (txtmasuk.getText().toString()){
                                case "": //jika txtmasuk isinya kosong maka (proses signin)
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss"); //buat format waktu
                                    String strTime = stf.format(c.getTime());
                                    txtmasuk.setText(strTime); //tampilkan waktu di txt masuk

                                    /*SharedPreferences prefs = getSharedPreferences("myData", MODE_PRIVATE);
                                    SharedPreferences.Editor mEditor = prefs.edit();
                                    mEditor.putString("signin", strTime);
                                    mEditor.commit();*/

                                    break;

                                default: //jika txtmasuk ada isinya (proses signout)

                                    c = Calendar.getInstance();
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy"); //buat format tanggal
                                    stf = new SimpleDateFormat("HH:mm:ss"); //buat format waktu
                                    String strDate = sdf.format(c.getTime());
                                    strTime = stf.format(c.getTime());
                                    TextView txtimei = (TextView) findViewById(R.id.txtimei);
                                    txtkeluar.setText(strTime); //tampilkan waktu di txtkeluar

                                    md5 md5 = new md5();

                                    String imei = md5.rmd5(txtimei.getText().toString()); //ambil imei dari txtimei lalu hash
                                    String date = strDate.toString();
                                    String signin = txtmasuk.getText().toString();
                                    String signout = strTime.toString();
                                    String dateStart = signin.toString(); //buat string start
                                    String dateStop = signout.toString(); //buat string stop

                                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

                                    Date d1 = null;
                                    Date d2 = null;

                                    try {
                                        d1 = format.parse(dateStart);
                                        d2 = format.parse(dateStop);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    long diff = d2.getTime() - d1.getTime(); //kurangi waktu stop dan start buat mencari selisih waktu

                                    long diffSeconds = diff / 1000 % 60;
                                    long diffMinutes = diff / (60 * 1000) % 60;
                                    long diffHours = diff / (60 * 60 * 1000) % 24;
                                    //long diffDays = diff / (24 * 60 * 60 * 1000);

                                    String total = diffHours + ":" + diffMinutes + ":" + diffSeconds; //buat variabel total untuk selisih waktu



                                    insertData(imei, date, signin, signout, total); //simpan semua di databse
                                    txtmasuk.setText("");
                                    txtkeluar.setText("");
                            }

                            Toast.makeText(MainActivity.this,"Behasil" ,Toast.LENGTH_LONG).show();


                        }else{

                            Toast.makeText(MainActivity.this,"Gagal" ,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ListQRModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //fungsi untuk memproses qr code



    private void insertData(String imei, String date, String signin, String signout, String total){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<InsertQRResponseModel> call = apiService.insertQR(imei, date, signin, signout, total); //mengirim semua data ke server untuk di query
        call.enqueue(new Callback<InsertQRResponseModel>() {
            @Override
            public void onResponse(Call<InsertQRResponseModel> call, Response<InsertQRResponseModel> response) {

                InsertQRResponseModel InsertQRResponseModel = response.body();

                //check the status code
                if(InsertQRResponseModel.getStatus()==1){
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertQRResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
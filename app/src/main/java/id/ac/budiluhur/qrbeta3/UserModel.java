package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 5/26/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("notelepon")
    @Expose
    private Object notelepon;
    @SerializedName("noktp")
    @Expose
    private String noktp;
    @SerializedName("tgllahir")
    @Expose
    private String tgllahir;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getNotelepon() {
        return notelepon;
    }

    public void setNotelepon(Object notelepon) {
        this.notelepon = notelepon;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}

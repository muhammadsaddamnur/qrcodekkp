package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 4/21/2017.
 */

/*
model untuk isi json QR
*/

 import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class QrModel {
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("signin")
    @Expose
    private String signin;
    @SerializedName("signout")
    @Expose
    private String signout;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("late")
    @Expose
    private String late;
    @SerializedName("over")
    @Expose
    private String over;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSignin() {
        return signin;
    }

    public void setSignin(String signin) {
        this.signin = signin;
    }

    public String getSignout() {
        return signout;
    }

    public void setSignout(String signout) {
        this.signout = signout;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

}
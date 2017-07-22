package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 4/21/2017.
 */


/*Model untuk membaca json QR (success dan message)*/
import com.google.gson.annotations.SerializedName;



public class InsertQRResponseModel {
    @SerializedName("success")
    private int status;
    @SerializedName("message")
    private String message;

    public InsertQRResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public InsertQRResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

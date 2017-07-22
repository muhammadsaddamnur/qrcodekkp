package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 4/21/2017.
 */

/*model untuk membaca json QR*/

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListQRModel {

    @SerializedName("QR")
    @Expose
    private List<QrModel> qR = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public ListQRModel(List<QrModel> qR, int status, String message) {
        this.qR = qR;
        this.status = status;
        this.message = message;
    }

    public ListQRModel() {
    }

    public List<QrModel> getQR() {
        return qR;
    }

    public void setQR(List<QrModel> qR) {
        this.qR = qR;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
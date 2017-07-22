package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 4/21/2017.
 */

/*Model untuk membaca json user*/

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUserModel {

    @SerializedName("QR")
    @Expose
    private List<UserModel> qR = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public ListUserModel(List<UserModel> qR, int status, String message) {
        this.qR = qR;
        this.status = status;
        this.message = message;
    }

    public ListUserModel() {
    }

    public List<UserModel> getQR() {
        return qR;
    }

    public void setQR(List<UserModel> qR) {
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
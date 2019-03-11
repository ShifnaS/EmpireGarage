package info.apatrix.empiregarage.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultList {
    @SerializedName("response")
    private ArrayList<Result> response;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public ArrayList<Result> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Result> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

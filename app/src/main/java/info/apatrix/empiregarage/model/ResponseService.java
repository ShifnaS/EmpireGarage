package info.apatrix.empiregarage.model;

import com.google.gson.annotations.SerializedName;

public class ResponseService {
    @SerializedName("response")
    private Responses response;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public Responses getResponse ()
    {
        return response;
    }

    public void setResponse (Responses response)
    {
        this.response = response;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}

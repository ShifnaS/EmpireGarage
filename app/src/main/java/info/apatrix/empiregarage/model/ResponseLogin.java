package info.apatrix.empiregarage.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("response")
    private Result response;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public Result getResponse ()
    {
        return response;
    }

    public void setResponse (Result response)
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

    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
    }


}


package info.apatrix.empiregarage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReportDefects  implements Parcelable {
    private String Others;

    private String Steering;

    private String Tyres;

    private String Battery;

    private String Suspension;

    private String Engine;


    public ReportDefects(String others, String steering, String tyres, String battery, String suspension, String engine) {
        Others = others;
        Steering = steering;
        Tyres = tyres;
        Battery = battery;
        Suspension = suspension;
        Engine = engine;
    }

    protected ReportDefects(Parcel in) {
        Others = in.readString();
        Steering = in.readString();
        Tyres = in.readString();
        Battery = in.readString();
        Suspension = in.readString();
        Engine = in.readString();
    }

    public static final Creator<ReportDefects> CREATOR = new Creator<ReportDefects>() {
        @Override
        public ReportDefects createFromParcel(Parcel in) {
            return new ReportDefects(in);
        }

        @Override
        public ReportDefects[] newArray(int size) {
            return new ReportDefects[size];
        }
    };



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Others);
        dest.writeString(Steering);
        dest.writeString(Tyres);
        dest.writeString(Battery);
        dest.writeString(Suspension);
        dest.writeString(Engine);
    }

    public String getOthers ()
    {
        return Others;
    }

    public void setOthers (String Others)
    {
        this.Others = Others;
    }

    public String getSteering ()
    {
        return Steering;
    }

    public void setSteering (String Steering)
    {
        this.Steering = Steering;
    }

    public String getTyres ()
    {
        return Tyres;
    }

    public void setTyres (String Tyres)
    {
        this.Tyres = Tyres;
    }

    public String getBattery ()
    {
        return Battery;
    }

    public void setBattery (String Battery)
    {
        this.Battery = Battery;
    }

    public String getSuspension ()
    {
        return Suspension;
    }

    public void setSuspension (String Suspension)
    {
        this.Suspension = Suspension;
    }

    public String getEngine ()
    {
        return Engine;
    }

    public void setEngine (String Engine)
    {
        this.Engine = Engine;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

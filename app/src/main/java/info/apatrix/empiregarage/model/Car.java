package info.apatrix.empiregarage.model;

public class Car {
    private String currentMileage;

    private String color;

    private String model;

    private String plateNumber;

    private String make;

    public String getCurrentMileage ()
    {
        return currentMileage;
    }

    public void setCurrentMileage (String currentMileage)
    {
        this.currentMileage = currentMileage;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public String getPlateNumber ()
    {
        return plateNumber;
    }

    public void setPlateNumber (String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public String getMake ()
    {
        return make;
    }

    public void setMake (String make)
    {
        this.make = make;
    }

}

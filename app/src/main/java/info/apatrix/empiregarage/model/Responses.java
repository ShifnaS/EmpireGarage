package info.apatrix.empiregarage.model;

import com.google.gson.annotations.SerializedName;

public class Responses {
    private String id;

    private Customer Customer;

    private Car Car;

    private ReportDefects ReportDefects;

    private ServicePackages ServicePackages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public info.apatrix.empiregarage.model.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(info.apatrix.empiregarage.model.Customer customer) {
        Customer = customer;
    }

    public info.apatrix.empiregarage.model.Car getCar() {
        return Car;
    }

    public void setCar(info.apatrix.empiregarage.model.Car car) {
        Car = car;
    }

    public info.apatrix.empiregarage.model.ReportDefects getReportDefects() {
        return ReportDefects;
    }

    public void setReportDefects(info.apatrix.empiregarage.model.ReportDefects reportDefects) {
        ReportDefects = reportDefects;
    }

    public info.apatrix.empiregarage.model.ServicePackages getServicePackages() {
        return ServicePackages;
    }

    public void setServicePackages(info.apatrix.empiregarage.model.ServicePackages servicePackages) {
        ServicePackages = servicePackages;
    }
}

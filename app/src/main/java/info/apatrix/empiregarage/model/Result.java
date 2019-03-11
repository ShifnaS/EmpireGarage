package info.apatrix.empiregarage.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    private String id;

    private String actionType;

    private String advisorId;

    private String car;

    private String plateNo;

    private String customerId;

    private String technician;

    private String startedDate;

    private String advisorName;

    private String technicianId;

    private String customer;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionType ()
    {
        return actionType;
    }

    public void setActionType (String actionType)
    {
        this.actionType = actionType;
    }

    public String getAdvisorId ()
    {
        return advisorId;
    }

    public void setAdvisorId (String advisorId)
    {
        this.advisorId = advisorId;
    }

    public String getCar ()
    {
        return car;
    }

    public void setCar (String car)
    {
        this.car = car;
    }

    public String getPlateNo ()
    {
        return plateNo;
    }

    public void setPlateNo (String plateNo)
    {
        this.plateNo = plateNo;
    }

    public String getCustomerId ()
    {
        return customerId;
    }

    public void setCustomerId (String customerId)
    {
        this.customerId = customerId;
    }

    public String getTechnician ()
    {
        return technician;
    }

    public void setTechnician (String technician)
    {
        this.technician = technician;
    }

    public String getStartedDate ()
    {
        return startedDate;
    }

    public void setStartedDate (String startedDate)
    {
        this.startedDate = startedDate;
    }

    public String getAdvisorName ()
    {
        return advisorName;
    }

    public void setAdvisorName (String advisorName)
    {
        this.advisorName = advisorName;
    }

    public String getTechnicianId ()
    {
        return technicianId;
    }

    public void setTechnicianId (String technicianId)
    {
        this.technicianId = technicianId;
    }

    public String getCustomer ()
    {
        return customer;
    }

    public void setCustomer (String customer)
    {
        this.customer = customer;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [actionType = "+actionType+", advisorId = "+advisorId+", car = "+car+", plateNo = "+plateNo+", customerId = "+customerId+", technician = "+technician+", startedDate = "+startedDate+", advisorName = "+advisorName+", technicianId = "+technicianId+", customer = "+customer+", status = "+status+"]";
    }

//////LoginResponse///////
    private String roleName ;

    private String roleId;

    private String userId;

    private String email;

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRoleName  ()
    {
        return roleName ;
    }

    public void setRoleName  (String roleName )
    {
        this.roleName  = roleName ;
    }

    public String getRoleId ()
    {
        return roleId;
    }

    public void setRoleId (String roleId)
    {
        this.roleId = roleId;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }







}

package info.apatrix.empiregarage.api;

import com.google.gson.JsonObject;

import info.apatrix.empiregarage.model.ResponseLogin;
import info.apatrix.empiregarage.model.ResponseService;
import info.apatrix.empiregarage.model.ResultList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("Technician_services/opened_services")
    Call<ResultList> getOpenedCarList(
            @Field("roleId") int rollId,
            @Field("userId") int userId,
            @Header("Authtoken") String Authtoken
    );

    @FormUrlEncoded
    @POST("Technician_services/completed_services")
    Call<ResultList> getClosedCarList(
            @Field("roleId") int rollId,
            @Field("userId") int userId,
            @Header("Authtoken") String Authtoken
    );


    @FormUrlEncoded
    @POST("Technician_services/requested_inventory_services")
    Call<ResultList> getRequestedCarList(
            @Field("roleId") int rollId,
            @Field("userId") int userId,
            @Header("Authtoken") String Authtoken
    );

    @FormUrlEncoded
    @POST("Technician_services/drew_out_inventory_services")
    Call<ResultList> getDewOutCarList(
            @Field("roleId") int rollId,
            @Field("userId") int userId,
            @Header("Authtoken") String Authtoken
    );



    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Settings/change_password")
    Call<ResponseLogin> changePassword(
            @Field("new_password") String new_password,
            @Field("user_id") int user_id,
            @Header("Authtoken") String Authtoken

    );

    @FormUrlEncoded
    @POST("Technician_services/services_detail")
    Call<ResponseService> getServiceDetail(
            @Field("job_id") String job_id,
            @Header("Authtoken") String Authtoken
    );
    @FormUrlEncoded
    @POST("Settings/forgot_password")
    Call<ResponseLogin> forgetPassword(
            @Field("email") String email,
            @Header("Authtoken") String Authtoken

    );


    @Headers({
            "Content-Type: application/json;",
    })
      @POST("Technician_services/request_material")
    Call<ResponseLogin> order(
            @Body JsonObject jsonObject,
            @Header("Authtoken") String Authtoken
            );
}

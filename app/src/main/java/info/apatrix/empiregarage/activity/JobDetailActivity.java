package info.apatrix.empiregarage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.apatrix.empiregarage.R;
import info.apatrix.empiregarage.api.APIService;
import info.apatrix.empiregarage.api.ApiModule;
import info.apatrix.empiregarage.model.Car;
import info.apatrix.empiregarage.model.Customer;
import info.apatrix.empiregarage.model.ReportDefects;
import info.apatrix.empiregarage.model.ResponseLogin;
import info.apatrix.empiregarage.model.ResponseService;
import info.apatrix.empiregarage.model.Responses;
import info.apatrix.empiregarage.model.ServicePackages;
import info.apatrix.empiregarage.utils.Constants;
import info.apatrix.empiregarage.utils.NetworkUtil;
import info.apatrix.empiregarage.utils.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView tv_name;
    @BindView(R.id.mobile)
    TextView tv_mobile;
    @BindView(R.id.email)
    TextView tv_email;
    @BindView(R.id.make)
    TextView tv_make;
    @BindView(R.id.model)
    TextView tv_model;
    @BindView(R.id.plateNo)
    TextView tv_plateNo;
    @BindView(R.id.color)
    TextView tv_color;
    @BindView(R.id.mileage)
    TextView tv_mileage;

    @BindView(R.id.service)
    TextView tv_service;
    @BindView(R.id.price)
    TextView tv_price;

    @BindView(R.id.other)
    TextView tv_other;
    @BindView(R.id.battery)
    TextView tv_battery;
    @BindView(R.id.steering)
    TextView tv_steering;
    @BindView(R.id.engine)
    TextView tv_engine;
    @BindView(R.id.suspension)
    TextView tv_suspension;
    @BindView(R.id.tyres)
    TextView tv_tyres;
    @BindView(R.id.start)
    Button bt_start;
    @BindView(R.id.id)
    TextView tv_job_id;
    String job_id="",auth_token,actionType;

    ReportDefects reportDefects=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_job_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        job_id= SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_JOB_ID);
        auth_token=SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_AUTH_TOKEN);
        actionType=SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_ACTION_TYPE);
      // auth_token="123456789";
        if(actionType.equals("2"))
        {
            bt_start.setVisibility(View.VISIBLE);
            bt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),StartActivity.class);
                    i.putExtra("reportDefect",reportDefects);
                    startActivity(i);
                }
            });
        }
        else if(actionType.equals("4"))
        {
            bt_start.setVisibility(View.VISIBLE);
            bt_start.setText("Complete Service");
            bt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent i=new Intent(getApplicationContext(),StartActivity.class);
                  //  startActivity(i);
                }
            });
        }

        if(NetworkUtil.isOnline())
        {
            getData();


        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }

    }

    private void getData() {


        APIService service = ApiModule.getAPIService();
        Call<ResponseService> call = service.getServiceDetail(job_id,auth_token);
        call.enqueue(new Callback<ResponseService>() {
            @Override
            public void onResponse(Call<ResponseService> call, Response<ResponseService> response) {

                try
                {
                    Toast.makeText(JobDetailActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    if(response.body()!=null&&response.isSuccessful())
                    {
                        if(response.body().getMessage().equals("successfully fetched"))
                        {
                            Responses obj=response.body().getResponse();
                            String id=obj.getId();
                            Customer customer=obj.getCustomer();
                            Car car=obj.getCar();
                            ServicePackages servicePackages=obj.getServicePackages();
                            reportDefects=obj.getReportDefects();

                            String customer_name=customer.getName();
                            String email=customer.getEmail();
                            String mobile=customer.getMobile();

                            tv_job_id.setText("JOB_ID: #"+id);
                            tv_name.setText(customer_name);
                            tv_email.setText(email);
                            tv_mobile.setText(mobile);

                            String make=car.getMake();
                            String model=car.getModel();
                            String color=car.getColor();
                            String mileage=car.getCurrentMileage();
                            String plate_no=car.getPlateNumber();

                            tv_make.setText(make);
                            tv_model.setText(model);
                            tv_color.setText(color);
                            tv_mileage.setText(mileage);
                            tv_plateNo.setText(plate_no);

                            String other=reportDefects.getOthers();
                            String battery=reportDefects.getBattery();
                            String suspension=reportDefects.getSuspension();
                            String engine=reportDefects.getEngine();
                            String steering=reportDefects.getSteering();
                            String tyre=reportDefects.getTyres();

                            tv_other.setText(other);
                            tv_battery.setText(battery);
                            tv_suspension.setText(suspension);
                            tv_engine.setText(engine);
                            tv_steering.setText(steering);
                            tv_tyres.setText(tyre);


                        }
                        else
                        {
                        }
                    }
                    else
                    {
                    }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());


                }

            }

            @Override
            public void onFailure(Call<ResponseService> call, Throwable t) {
                //       progressBar.setVisibility(View.GONE);

                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });


    }

}

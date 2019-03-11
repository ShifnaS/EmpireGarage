package info.apatrix.empiregarage.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.apatrix.empiregarage.R;
import info.apatrix.empiregarage.model.ReportDefects;
import info.apatrix.empiregarage.utils.Constants;
import info.apatrix.empiregarage.utils.SharedPreferenceUtils;

public class StartActivity extends AppCompatActivity {
    String job_id="",auth_token,other="", battery="",suspension="",engine="",steering="",tyre="";
    int rollId,userId;

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


    ///status

    @BindView(R.id.other_status)
    TextView tv_other_status;
    @BindView(R.id.battery_status)
    TextView tv_battery_status;
    @BindView(R.id.steering_status)
    TextView tv_steering_status;
    @BindView(R.id.engine_status)
    TextView tv_engine_status;
    @BindView(R.id.suspension_status)
    TextView tv_suspension_status;
    @BindView(R.id.tyres_status)
    TextView tv_tyres_status;

    ///material

    @BindView(R.id.other_material)
    TextView tv_other_material;
    @BindView(R.id.battery_material)
    TextView tv_battery_material;
    @BindView(R.id.steering_material)
    TextView tv_steering_material;
    @BindView(R.id.engine_material)
    TextView tv_engine_material;
    @BindView(R.id.suspension_material)
    TextView tv_suspension_material;
    @BindView(R.id.tyres_material)
    TextView tv_tyres_material;

//quantity
    @BindView(R.id.other_quantity)
    TextView tv_other_quantity;
    @BindView(R.id.battery_quantity)
    TextView tv_battery_quantity;
    @BindView(R.id.steering_quantity)
    TextView tv_steering_quantity;
    @BindView(R.id.engine_quantity)
    TextView tv_engine_quantity;
    @BindView(R.id.suspension_quantity)
    TextView tv_suspension_quantity;
    @BindView(R.id.tyres_quantity)
    TextView tv_tyres_quantity;


    @BindView(R.id.id)
    TextView tv_id;
    @BindView(R.id.requsest)
    Button tv_requsest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        job_id= SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_JOB_ID);
        auth_token=SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_AUTH_TOKEN);
        rollId= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_ROLE_ID);
        userId=SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);
        ButterKnife.bind(this);

        tv_id.setText("Gold Maintanence Package (Job ID #"+job_id+")");
        ReportDefects reportDefects = getIntent().getParcelableExtra("reportDefect");
        if (reportDefects != null) {
             other=reportDefects.getOthers();
             battery=reportDefects.getBattery();
             suspension=reportDefects.getSuspension();
             engine=reportDefects.getEngine();
             steering=reportDefects.getSteering();
             tyre=reportDefects.getTyres();

            tv_other.setText(other);
            tv_battery.setText(battery);
            tv_suspension.setText(suspension);
            tv_engine.setText(engine);
            tv_steering.setText(steering);
            tv_tyres.setText(tyre);

        }
        tv_requsest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMaterial();
            }
        });
    }

    private void requestMaterial() {

     //   String other=

    }
}

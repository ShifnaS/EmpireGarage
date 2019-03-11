package info.apatrix.empiregarage.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.OnClick;
import info.apatrix.empiregarage.R;
import info.apatrix.empiregarage.adapter.CarAdapter;
import info.apatrix.empiregarage.adapter.RecyclerTouchListener;
import info.apatrix.empiregarage.api.APIService;
import info.apatrix.empiregarage.api.ApiModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.apatrix.empiregarage.model.Result;
import info.apatrix.empiregarage.model.ResultList;
import info.apatrix.empiregarage.utils.Constants;
import info.apatrix.empiregarage.utils.NetworkUtil;
import info.apatrix.empiregarage.utils.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.service)
    TextView tv_service;
    @BindView(R.id.opened)
    ImageView _opened;
    @BindView(R.id.requested)
    ImageView _requested;
    @BindView(R.id.drewout)
    ImageView _drewout;
    @BindView(R.id.completed)
    ImageView _completed;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.filter)
    ImageView filter;
    @BindView(R.id.mymenu)
    ImageView menus;
    @BindView(R.id.toolbar)
    Toolbar topToolBar;
    CarAdapter mAdapter;
    List<Result> carList= new ArrayList<>();
    String service="";
    int rollId,userId;
    String token="";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        setSupportActionBar(topToolBar);
        ButterKnife.bind(this);
        _opened.setOnClickListener(this);
        _requested.setOnClickListener(this);
        _drewout.setOnClickListener(this);
        _completed.setOnClickListener(this);
        menus.setOnClickListener(this);
        filter.setOnClickListener(this);
        progressDialog = new ProgressDialog(HomeActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        rollId= SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_ROLE_ID);
        userId=SharedPreferenceUtils.getInstance(getApplicationContext()).getIntValue(Constants.KEY_USER_ID);
        //token="123456789";
        token=SharedPreferenceUtils.getInstance(getApplicationContext()).getStringValue(Constants.KEY_AUTH_TOKEN);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        service="opened";
        _opened.setImageResource(R.drawable.ic_opened_services_active);
        if(NetworkUtil.isOnline())
        {
            prepareCarData(service);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);


        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                if(NetworkUtil.isOnline())
                {
                    prepareCarData(service);

                }
                else
                {
                    mSwipeRefreshLayout.setRefreshing(false);

                    Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
                }

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // Movie movie = movieList.get(position);
                Result data=carList.get(position);
                SharedPreferenceUtils.getInstance(getApplicationContext()).setStringValue(Constants.KEY_JOB_ID,data.getId());
                SharedPreferenceUtils.getInstance(getApplicationContext()).setStringValue(Constants.KEY_ACTION_TYPE,data.getActionType());

                // Toast.makeText(getApplicationContext(),  " is selected! "+data.getId(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),JobDetailActivity.class);
                startActivity(i);
              //  finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void prepareCarData(String serviceType) {
        mSwipeRefreshLayout.setRefreshing(false);

        progressDialog.show();

        APIService services = ApiModule.getAPIService();
        Call<ResultList> call=null;

        if(serviceType.equals("opened"))
        {
            call = services.getOpenedCarList(rollId,userId,token);
        }
        else if(serviceType.equals("closed"))
        {
            call = services.getClosedCarList(rollId,userId,token);
        }
        else if(serviceType.equals("requested"))
        {
            call = services.getRequestedCarList(rollId,userId,token);
        }
        else if(serviceType.equals("dewout"))
        {
            call = services.getDewOutCarList(rollId,userId,token);
        }

        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                mSwipeRefreshLayout.setRefreshing(false);
                progressDialog.dismiss();
                try
                {
                    Toast.makeText(HomeActivity.this, "hiii "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if(response.body().getMessage().equals("successfully fetched"))
                    {
                        carList=response.body().getResponse();
                        mAdapter = new CarAdapter(carList,getApplicationContext());
                        recyclerView.setAdapter(mAdapter);
                       // service="";
                      //  mAdapter.notifyDataSetChanged();

                        // mAdapter.notifyDataSetChanged();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No data in this category", Toast.LENGTH_SHORT).show();
                    }

                   // Log.e("item","my item size "+item.size());

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Exception ",e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                Log.e("MyTag", "requestFailed", t);
                Log.e("Failure ",t.getMessage());

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.opened:
                // do your code
                service="opened";
                topToolBar.setTitle("Opened Services");
                tv_service.setText("Opened Services");
                _opened.setImageResource(R.drawable.ic_opened_services_active);
                _requested.setImageResource(R.drawable.ic_requested_inventory);
                _drewout.setImageResource(R.drawable.ic_drew_out_inventory);
                _completed.setImageResource(R.drawable.ic_completed_services);
                if(NetworkUtil.isOnline())
                {
                    prepareCarData(service);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.requested:
                // do your code
                service="requested";
                topToolBar.setTitle("Requested Inventory");
                tv_service.setText("Requested Inventory");
                _requested.setImageResource(R.drawable.ic_requested_inventory_active);
                _opened.setImageResource(R.drawable.ic_opened_services);
                _drewout.setImageResource(R.drawable.ic_drew_out_inventory);
                _completed.setImageResource(R.drawable.ic_completed_services);
                if(NetworkUtil.isOnline())
                {
                    prepareCarData(service);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.drewout:
                // do your code
                service="dewout";
                topToolBar.setTitle("DrewOut Inventory");
                tv_service.setText("DrewOut Inventory");
                _drewout.setImageResource(R.drawable.ic_ic_drew_out_inventory_active);
                _opened.setImageResource(R.drawable.ic_opened_services);
                _requested.setImageResource(R.drawable.ic_requested_inventory);
                _completed.setImageResource(R.drawable.ic_completed_services);
                if(NetworkUtil.isOnline())
                {
                    prepareCarData(service);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.completed:
                // do your code
                service="closed";
                topToolBar.setTitle("Completed Services");
                tv_service.setText("Completed Services");
                _completed.setImageResource(R.drawable.ic_completed_services_active);
                _opened.setImageResource(R.drawable.ic_opened_services);
                _requested.setImageResource(R.drawable.ic_requested_inventory);
                _drewout.setImageResource(R.drawable.ic_drew_out_inventory);
                if(NetworkUtil.isOnline())
                {
                    prepareCarData(service);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.filter:
                showBottomSheetDialog();
                break;
            case R.id.mymenu:
                Toast.makeText(this, "fghfghfh", Toast.LENGTH_SHORT).show();
               setOption();
                break;
            default:
                break;
        }

    }

    private void setOption() {
        //Creating the instance of PopupMenu
        Context wrapper = new ContextThemeWrapper(HomeActivity.this, R.style.PopUpTheme);

        PopupMenu popup = new PopupMenu(wrapper, menus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            popup.setGravity(Gravity.END);
        }

        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Intent i;
                Toast.makeText(HomeActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (item.getTitle().toString())
                {
                    case "Profile" :
                        i=new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(i);
                        break;
                    case "Change Password" :
                        i=new Intent(getApplicationContext(),ChangePassword.class);
                        startActivity(i);
                        break;
                    case "Logout" :
                       // showBottomSheetDialog();
                        SharedPreferenceUtils.getInstance(getApplicationContext()).clear();
                        i=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        popup.show();//showing popup menu
    }

    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;

    }

    @Override
    public void onRefresh() {

        if(NetworkUtil.isOnline())
        {
            prepareCarData(service);
        }
        else
        {
            mSwipeRefreshLayout.setRefreshing(false);

            Toast.makeText(getApplicationContext(), "Please check your network", Toast.LENGTH_SHORT).show();
        }

    }


}

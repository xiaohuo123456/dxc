package com.bawei.shens.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bawei.shens.MainActivity;
import com.bawei.shens.R;
import com.bawei.shens.base.BaseActivity;
import com.bawei.shens.base.BasePresenter;
import com.bawei.shens.bean.LoginBean;
import com.bawei.shens.myapp.MyApp;
import com.bawei.shens.presenter.MyPresenter;
import com.bawei.shens.urls.MyUrl;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class WelComeActivity extends BaseActivity {
    private ImageView imageView;
    private TextView textView;
    private Button button;
    int i = 5;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int a = (int) msg.obj;
            if (msg.what == 1){
                if (a<=0){
                    Log.e("msg", "handleMessage: "+a);
                    to_HomePage();
                }else {
                    button.setText(msg.obj+"s");
                }
            }
        }
    };
    private SharedPreferences shens;
    private SharedPreferences.Editor edit;

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
        shens = MyApp.context.getSharedPreferences("shens", MODE_PRIVATE);
        edit = shens.edit();
        button.setAlpha(0.8f);
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while (i>=0){
                        try {
                            handler.sendMessage(handler.obtainMessage(1,i--));
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        ).start();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = -1;
                handler.sendMessage(handler.obtainMessage(1,i));
            }
        });
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.welcome_image);
        button = findViewById(R.id.welcome_button);
        textView = findViewById(R.id.to_mainActivity);
    }

    @Override
    protected int initLayout() {
        return R.layout.welcome_activity;
    }
    public void to_HomePage(){
//        String status = shens.getString("status", "");
//        if (status.equals("0000")){
//            Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }else if (status.equals("1001")){
//            String phone = shens.getString("phone", "");
//            String pwd = shens.getString("pwd", "");
//            if (phone.isEmpty()){
                Intent intent = new Intent(WelComeActivity.this,Login_Activity.class);
                startActivity(intent);
                finish();
//            }else {
//                Map<String,String > map = new HashMap<>();
//                map.put("phone",phone);
//                map.put("pwd",pwd);
//                p.login_Mode(MyUrl.LOGINURL,map, LoginBean.class);
//            }
//        }
    }

    @Override
    public void onSuccessful(Object o) {
        Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Intent intent = new Intent(WelComeActivity.this,Login_Activity.class);
        startActivity(intent);
        finish();
    }
}

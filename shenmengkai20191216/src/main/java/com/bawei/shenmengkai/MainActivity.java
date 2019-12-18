package com.bawei.shenmengkai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.shenmengkai.base.BaseActivity;
import com.bawei.shenmengkai.base.BasePresenter;
import com.bawei.shenmengkai.bean.UserBean;
import com.bawei.shenmengkai.myapp.MyApp;
import com.bawei.shenmengkai.presenter.MyPresenter;
import com.bawei.shenmengkai.urls.MyUrl;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {
    private EditText edit_phone;
    private EditText edit_pwd;
    private Button login_btn;

    @Override
    protected void initData() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                Map<String,Object> map = new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);
                p.login(MyUrl.LOGINURL,map);
            }
        });
    }

    @Override
    protected void initView() {
        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);
        login_btn = findViewById(R.id.login_btn);


    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccessful(String str) {
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(str, UserBean.class);
        int userId = bean.getResult().getUserId();
        String sessionId = bean.getResult().getSessionId();
        SharedPreferences shenmengkai = MyApp.context.getSharedPreferences("shenmengkai", MODE_PRIVATE);
        SharedPreferences.Editor edit = shenmengkai.edit();
        edit.putString("userId",userId+"").commit();
        edit.putString("sessionId",sessionId).commit();
        Intent intent = new Intent(MainActivity.this,ShowActivity.class);
        intent.putExtra("userId",bean.getResult().getUserId());
        intent.putExtra("sessionId",bean.getResult().getSessionId());
        intent.putExtra("head_pic",bean.getResult().getHeadPic());
        intent.putExtra("name",bean.getResult().getNickName());
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Log.e("sss", "error: "+error );
    }


}

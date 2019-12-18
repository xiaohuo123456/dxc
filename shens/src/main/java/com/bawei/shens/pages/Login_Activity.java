package com.bawei.shens.pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Login_Activity extends BaseActivity {

    private EditText edit_login_phone;
    private EditText edit_login_pwd;
    private Button btn_login_pwd;
    private SharedPreferences shens;
    private SharedPreferences.Editor edit;

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initData() {
        btn_login_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_phone = edit_login_phone.getText().toString();
                String edit_pwd = edit_login_pwd.getText().toString();
                shens = MyApp.context.getSharedPreferences("shens",MODE_PRIVATE);
                edit = shens.edit();
                edit.putString("phone",edit_phone).commit();
                edit.putString("pwd",edit_pwd).commit();
                Map<String,String > map = new HashMap<>();
                map.put("phone",edit_phone);
                map.put("pwd",edit_pwd);
                p.login_Mode(MyUrl.LOGINURL,map,LoginBean.class);
            }
        });
    }

    @Override
    protected void initView() {
        edit_login_phone = findViewById(R.id.edit_login_phone);
        edit_login_pwd = findViewById(R.id.edit_login_pwd);
        btn_login_pwd = findViewById(R.id.btn_login_pwd);
    }

    @Override
    protected int initLayout() {
        return R.layout.login_activity;
    }


    @Override
    public void onSuccessful(Object o) {
        if (o instanceof LoginBean){
            shens = MyApp.context.getSharedPreferences("shens", MODE_PRIVATE);
            edit = shens.edit();
            String s = ((LoginBean) o).getStatus();
            edit.putString("status",s).commit();
            String status = shens.getString("status", "");
            Log.e("TAg", "onSuccessful: "+status);
            Log.e("TAg", "onSuccessful: "+s);
            edit.putString("userId",((LoginBean) o).getResult().getUserId()+"").commit();
            edit.putString("sessionId",((LoginBean) o).getResult().getSessionId()).commit();
            Log.e("TAG", "onError: "+((LoginBean) o).getResult().getNickName());
            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onError(String error) {
        edit.putString("status","1001");
    }
}

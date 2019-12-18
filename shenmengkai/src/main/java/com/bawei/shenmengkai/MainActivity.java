package com.bawei.shenmengkai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bawei.shenmengkai.urls.MyUrls;
import com.bawei.shenmengkai.utils.NetWorkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkUtils.getInstance().getData(MyUrls.BANNERURL, new NetWorkUtils.DataCallBack() {
            @Override
            public void onSuccessful(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

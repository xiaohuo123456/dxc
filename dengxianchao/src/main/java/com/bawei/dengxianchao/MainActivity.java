package com.bawei.dengxianchao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bawei.dengxianchao.url.MyUrl;
import com.bawei.dengxianchao.utils.RetorfitUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetorfitUtils.getInstance().getJson(MyUrl.BANNERURL, new RetorfitUtils.DataCallBack() {
            @Override
            public void onSuccessful(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

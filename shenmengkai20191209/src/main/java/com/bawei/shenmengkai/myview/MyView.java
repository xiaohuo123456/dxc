package com.bawei.shenmengkai.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawei.shenmengkai.R;

public class MyView extends LinearLayout {
    private TextView add,count,remove;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.addandremove_layout,this);
        add = findViewById(R.id.add_myview);
        count = findViewById(R.id.count_myview);
        remove = findViewById(R.id.remove_myview);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.valueOf(count.getText().toString());
                number++;
                Log.e("TAG", "onClick: "+mClickCallBack);
                mClickCallBack.setCount(number);
            }
        });
    }
    public interface ClickCallBack{
        void setCount(int number);
    }
    public static ClickCallBack mClickCallBack;

    public  void setClickCallBack(ClickCallBack clickCallBack){
        mClickCallBack = clickCallBack;
    }
    public void setCount(int number){
        count.setText(number+"");
    }
}

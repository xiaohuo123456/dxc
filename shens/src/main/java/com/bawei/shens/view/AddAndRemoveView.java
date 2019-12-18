package com.bawei.shens.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.shens.R;

public class AddAndRemoveView extends LinearLayout {
    private TextView add,count,remove;

    public AddAndRemoveView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.addandremove_layout,this);
        add = findViewById(R.id.add_myview);
        count = findViewById(R.id.count_myview);
        remove = findViewById(R.id.remove_myview);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = count.getText().toString();
                int number = Integer.valueOf(s);
                number+=1;
                Log.e("aaaa", "onClick: "+number );
                clickCallBack.getItemCount(number);
            }
        });
        remove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = count.getText().toString();
                int number = Integer.valueOf(s);
                if (number>1){
                    number--;
                    clickCallBack.getItemCount(number);
                }else {
                    Toast.makeText(context,"不能小与1",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public interface ClickCallBack{
        void getItemCount(int number);
    }
    public ClickCallBack clickCallBack;

    public void getClickCallBack(ClickCallBack callBack) {
        clickCallBack = callBack;
    }

    public void setNumber(int number){
        count.setText(number+"");
    }


}

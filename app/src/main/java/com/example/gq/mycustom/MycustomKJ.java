package com.example.gq.mycustom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gq on 2018/1/15.
 */

public class MycustomKJ extends LinearLayout {

    private TextView next01;
    private TextView text02;

    public MycustomKJ(Context context) {
        this(context,null);
    }
     public MycustomKJ(Context context, @Nullable AttributeSet attrs) {
         this(context, attrs,0);

     }
      public MycustomKJ(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

       //加载item布局咱们一般会使用
        View view = LayoutInflater.from(context).inflate(R.layout.item, this);

        next01 = findViewById(R.id.next01);
        text02 = findViewById(R.id.text02);

       addView(view);//添加视图

    }

}

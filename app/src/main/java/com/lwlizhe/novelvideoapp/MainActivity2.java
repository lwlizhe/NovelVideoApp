package com.lwlizhe.novelvideoapp;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.lwlizhe.novelvideoapp.widget.ExpandTextView;
import com.lwlizhe.novelvideoapp.widget.TestView;
import com.lwlizhe.novelvideoapp.widget.novelPage.novelPage.NovelPage;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MainActivity2 extends AppCompatActivity {

    ExpandTextView mTvwIntroduction;

    private FrameLayout mRootView;
    private NovelPage mPage;
    private TestView mPage2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final View view = LayoutInflater.from(MainActivity2.this).inflate(R.layout.layout_pop_dialog, null, false);
//        final PopupWindow window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        window.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
//        window.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
//        window.setFocusable(true);
//
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//
//        final int popupWidth = view.getMeasuredWidth();    //  获取测量后的宽度
//        final int popupHeight = view.getMeasuredHeight();  //获取测量后的高度
//
//        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int[] location = new int[2];
//                v.getLocationOnScreen(location);
//
//                window.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
//            }
//        });
//
//        mRootView=findViewById(R.id.root_view);
//
//        mPage = new NovelPage(this);
//        mPage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//
//        if(mPage.getParent()!=null){
//            mPage.bringToFront();
//        }else{
//            mRootView.removeAllViews();
//            mRootView.addView(mPage);
//        }
//
//        mPage.setContent(getResources().getString(R.string.test));
//
//
//        mTvwIntroduction=findViewById(R.id.expand_text_view);
//
//        mTvwIntroduction.setMinVisibleLines(2);
////        mTvwIntroduction.setContent(getString(R.string.test2));
//
//        ImageView test=findViewById(R.id.img_test);
//
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .transform(new RectangleBorderImageTransform(this,20))
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .priority(Priority.HIGH);
//
//
//        Glide.with(this).load(R.mipmap.test)
//                .apply(options)
//                .into(test);


        try {
//            Class<?> invokeClass=Class.forName("com.lwlizhe.novelvideoapp.TestInterface");
            Class<?> invokeClass=TestInterface.class.getClass();

            Field[] declaredFields = invokeClass.getDeclaredFields();

            StringBuilder sb=new StringBuilder();

            for(Field f:declaredFields){

                sb.append(f.get(f.getType()));
            }

            Toast.makeText(MainActivity2.this,sb.toString(),Toast.LENGTH_SHORT).show();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}

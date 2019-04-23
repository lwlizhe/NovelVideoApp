package com.lwlizhe.novelvideoapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwlizhe.basemodule.widget.TextViewUtils;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MainActivity2 extends AppCompatActivity {

    TextView tvwContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvwContent = findViewById(R.id.tv_test);
        tvwContent.setText("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTest\nTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextPaint sTempTextPaint = new TextPaint();

                sTempTextPaint.set(tvwContent.getPaint());
                sTempTextPaint.setTextSize(tvwContent.getTextSize());

                StaticLayout staticLayout = TextViewUtils.createStaticLayout(tvwContent, tvwContent.getText(), sTempTextPaint, Integer.MAX_VALUE);
                sTempTextPaint.reset();



                Toast.makeText(MainActivity2.this, TextViewUtils.measureTextHeight(tvwContent, tvwContent.getText()) + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

package com.a3rdlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.CommUtil;
import com.selflibrary.utils.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T.showLong(this,"这是aar中的文件");

        CommUtil.getDate();

    }
}

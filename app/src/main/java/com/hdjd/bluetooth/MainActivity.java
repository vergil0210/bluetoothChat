package com.hdjd.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hdjd.bluetooth.threadPool.ExecutorFactory;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorFactory.getScheduleExecutor().schedule(() ->{
            startActivity(new Intent(this,FirstActivity.class));
            finish();
        },1, TimeUnit.SECONDS);
    }
}
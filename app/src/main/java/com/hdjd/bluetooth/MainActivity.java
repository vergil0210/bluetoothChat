package com.hdjd.bluetooth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.hdjd.bluetooth.threadPool.ExecutorFactory;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter==null){
            // don't support bluetooth
            Toast.makeText(this,"your device don't support bluetooth,It's will finish in 3 seconds",Toast.LENGTH_SHORT).show();
            ExecutorFactory.getScheduleExecutor().schedule(this::finish,3,TimeUnit.SECONDS);
        }
        if (!mBluetoothAdapter.isEnabled()){
            // support bluetooth
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),REQUEST_ENABLE_BT);
        } else {
            // jump to firstActivity
            ExecutorFactory.getScheduleExecutor().schedule(() ->{
                startActivity(new Intent(this,FirstActivity.class));
                finish();
            },1, TimeUnit.SECONDS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode==RESULT_OK){
                    Toast.makeText(this,"is opening bluetooth",Toast.LENGTH_SHORT).show();
                    // jump to firstActivity
                    ExecutorFactory.getScheduleExecutor().schedule(() ->{
                        startActivity(new Intent(this,FirstActivity.class));
                        finish();
                    },1, TimeUnit.SECONDS);
                } else{
                    Toast.makeText(this,"we can't get permission to open bluetooth,It's will finish in 3 seconds",Toast.LENGTH_SHORT).show();
                    ExecutorFactory.getScheduleExecutor().schedule(this::finish,3,TimeUnit.SECONDS);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
package com.hdjd.bluetooth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {


    private ListView listView;
    private final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice pairedDevice : pairedDevices) {
            Log.i(TAG, "device: name:"+pairedDevice.getName()+",address"+pairedDevice.getAddress()+",alias"+pairedDevice.getAlias());
        }
        List<BluetoothDevice> devices = new ArrayList<>(pairedDevices);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new MyAdaptor(devices));

    }



    private class MyAdaptor extends BaseAdapter{

        private List<BluetoothDevice> devices;
        public MyAdaptor(@NonNull List<BluetoothDevice> devices) {
            this.devices = devices;
        }

        @Override
        public int getCount() {
            return devices.size();
        }

        @Override
        public Object getItem(int position) {
            return devices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 第一个参数为上下文 第二个参数为资源ID 第三个参数定义该View的父容器ViewGroup 如（LinearLayout)
            View view  = View.inflate(SearchActivity.this, R.layout.search_item_view, null);
            TextView tv =  view.findViewById(R.id.tv_name);
            tv.setText(devices.get(position).getName());
            return view;
        }
    }
}
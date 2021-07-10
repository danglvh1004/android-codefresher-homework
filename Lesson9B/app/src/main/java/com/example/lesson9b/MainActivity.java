package com.example.lesson9b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAddress;
    private TextView tvPhone;

    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        configureReceiver();
    }

    private void initUI() {
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
    }

    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.lesson9a.BROADCAST_ACTION");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();

            String strName = intent.getStringExtra("name_contact");
            String strAddress = intent.getStringExtra("address_contact");
            String strPhone = intent.getStringExtra("phone_contact");

            tvName.setText(strName);
            tvAddress.setText(strAddress);
            tvPhone.setText(strPhone);
        }
    }
}
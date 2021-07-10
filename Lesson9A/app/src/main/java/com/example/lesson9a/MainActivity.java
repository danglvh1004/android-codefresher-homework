package com.example.lesson9a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtPhone;
    private Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                String strName = edtName.getText().toString().trim();
                String strAddress = edtAddress.getText().toString().trim();
                String strPhone = edtPhone.getText().toString().trim();

                intent.putExtra("name_contact", strName);
                intent.putExtra("address_contact", strAddress);
                intent.putExtra("phone_contact", strPhone);
//                intent.putExtras(bundle);
                intent.setAction("com.example.lesson9a.BROADCAST_ACTION");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });
    }

    private void initUI() {
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phone);
        btnSendBroadcast = findViewById(R.id.btn_send_broadcast);
    }
}
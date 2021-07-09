package com.example.lesson8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {

    private ImageView imgBackAdd;
    private EditText edtNameAdd;
    private EditText edtAddressAdd;
    private EditText edtPhoneAdd;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initUI();

        imgBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = edtNameAdd.getText().toString().trim();
                String strAddress = edtAddressAdd.getText().toString().trim();
                String strPhone = edtPhoneAdd.getText().toString().trim();

                Intent intentAdd = new Intent();
                intentAdd.putExtra("contact_name_add", strName);
                intentAdd.putExtra("contact_address_add", strAddress);
                intentAdd.putExtra("contact_phone_add", strPhone);
                setResult(Activity.RESULT_OK, intentAdd);
                AddActivity.this.finish();
            }
        });
    }

    private void initUI() {
        imgBackAdd = findViewById(R.id.img_back_add);
        edtNameAdd = findViewById(R.id.edt_name_add);
        edtAddressAdd = findViewById(R.id.edt_address_add);
        edtPhoneAdd = findViewById(R.id.edt_phone_add);
        btnAdd = findViewById(R.id.btn_add);
    }
}
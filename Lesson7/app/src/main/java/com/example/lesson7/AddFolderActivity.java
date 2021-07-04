package com.example.lesson7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFolderActivity extends AppCompatActivity {

    private TextView tvAdd;
    private TextView tvCancel;
    private EditText edtTitleAdd;
    private EditText edtContentAdd;

    private void initUI() {
        tvAdd = findViewById(R.id.tv_add);
        tvCancel = findViewById(R.id.tv_cancel);
        edtTitleAdd = findViewById(R.id.edt_title_add);
        edtContentAdd = findViewById(R.id.edt_content_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initUI();

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelActivity();
            }
        });
    }

    private void saveItem() {
        String titleAdd = edtTitleAdd.getText().toString().trim();
        String contentAdd = edtContentAdd.getText().toString().trim();

        if ((titleAdd.isEmpty()) || (contentAdd.isEmpty())) {
            displayToast("Please insert a title and a content");
            return;
        }

        Intent intentAdd = new Intent();
        intentAdd.putExtra("titleAddData", titleAdd);
        intentAdd.putExtra("contentAddData", contentAdd);

        setResult(Activity.RESULT_OK, intentAdd);
        AddFolderActivity.this.finish();
    }

    private void cancelActivity() {
        setResult(Activity.RESULT_CANCELED);
        AddFolderActivity.this.finish();
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
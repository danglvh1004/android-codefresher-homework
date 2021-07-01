package com.example.lesson6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    private TextView tvSave;
    private TextView tvCancel;
    private EditText edtTitleEdit;
    private EditText edtContentEdit;

    public void initUI() {
        tvSave = findViewById(R.id.tv_save);
        tvCancel = findViewById(R.id.tv_cancel);
        edtTitleEdit = findViewById(R.id.edt_title_edit);
        edtContentEdit = findViewById(R.id.edt_content_edit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initUI();

        getDataFromAdapter();

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItem();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelActivity();
            }
        });
    }

    String positionItem1 = "";
    private void getDataFromAdapter() {
        Intent intentGetData = getIntent();
        edtTitleEdit.setText(intentGetData.getStringExtra("titleData1"));
        edtContentEdit.setText(intentGetData.getStringExtra("contentData1"));
        positionItem1 = intentGetData.getStringExtra("positionItem1");
        displayToast(positionItem1);
    }

    private void editItem() {
        Intent intentUpdate = new Intent();
        intentUpdate.putExtra("titleUpdate", edtTitleEdit.getText().toString().trim());
        intentUpdate.putExtra("contentUpdate", edtContentEdit.getText().toString().trim());

        setResult(Activity.RESULT_OK, intentUpdate);
        finish();
    }

    private void cancelActivity() {
        setResult(Activity.RESULT_CANCELED);
        EditItemActivity.this.finish();
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
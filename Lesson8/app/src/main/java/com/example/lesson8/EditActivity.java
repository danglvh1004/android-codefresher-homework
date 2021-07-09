package com.example.lesson8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity {

    private ImageView imgBackEdit;
    private EditText edtNameEdit;
    private EditText edtAddressEdit;
    private EditText edtPhoneEdit;
    private Button btnSave;

    private Contact contactEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initUI();

        imgBackEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        contactEdit = (Contact) getIntent().getExtras().get("contact_edit");
        if (contactEdit != null) {
            edtNameEdit.setText(contactEdit.getmName());
            edtAddressEdit.setText(contactEdit.getmAddress());
            edtPhoneEdit.setText(contactEdit.getmPhone());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strIdEdit = String.valueOf(contactEdit.getmId());
                String strNameEdit = edtNameEdit.getText().toString().trim();
                String strAddressEdit = edtAddressEdit.getText().toString().trim();
                String strPhoneEdit = edtPhoneEdit.getText().toString().trim();

                if (TextUtils.isEmpty(strNameEdit) || TextUtils.isEmpty(strAddressEdit) || TextUtils.isEmpty(strPhoneEdit)) {
                    return;
                }

                Intent intentEdit = new Intent(EditActivity.this, MainActivity.class);
                intentEdit.putExtra("contact_id_edit", strIdEdit);
                intentEdit.putExtra("contact_name_edit", strNameEdit);
                intentEdit.putExtra("contact_address_edit", strAddressEdit);
                intentEdit.putExtra("contact_phone_edit", strPhoneEdit);
                setResult(Activity.RESULT_OK, intentEdit);
                EditActivity.this.finish();
            }
        });
    }

    private void initUI() {
        imgBackEdit = findViewById(R.id.img_back_edit);
        edtNameEdit = findViewById(R.id.edt_name_edit);
        edtAddressEdit = findViewById(R.id.edt_address_edit);
        edtPhoneEdit = findViewById(R.id.edt_phone_edit);
        btnSave = findViewById(R.id.btn_save);
    }
}
package com.example.lesson7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditFolderActivity extends AppCompatActivity {

    private TextView tvSave;
    private TextView tvCancel;
    private EditText edtTitleEdit;
    private EditText edtContentEdit;

    private Folder folder;
    private FolderViewModel mFolderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvSave = findViewById(R.id.tv_save);
        tvCancel = findViewById(R.id.tv_cancel);
        edtTitleEdit = findViewById(R.id.edt_title_edit);
        edtContentEdit = findViewById(R.id.edt_content_edit);

        folder = (Folder) getIntent().getExtras().get("object_folder");
        if (folder != null) {
            edtTitleEdit.setText(folder.getTitle());
            edtContentEdit.setText(folder.getContent());
        }

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

    private void editItem() {
        String strTitle = edtTitleEdit.getText().toString().trim();
        String strContent = edtContentEdit.getText().toString().trim();

        if (TextUtils.isEmpty(strTitle) || TextUtils.isEmpty(strContent)) {
            return;
        }

        folder.setTitle(strTitle);
        folder.setContent(strContent);

        FolderDatabase.getDatabase(this).folderDao().updateFolder(folder);

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

    private void cancelActivity() {
        setResult(Activity.RESULT_CANCELED);
        EditFolderActivity.this.finish();
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
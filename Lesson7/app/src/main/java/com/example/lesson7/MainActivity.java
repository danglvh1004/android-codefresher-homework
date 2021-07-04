/**/package com.example.lesson7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv_list;
    private FolderAdapter folderAdapter;
    private TextView tvAdd;

    private static final int ADD_ITEM_REQUEST = 1;
    private static final int EDIT_ITEM_REQUEST = 2;

    private int positionItemUpdate = -1;

    private FolderViewModel mFolderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        mFolderViewModel = new ViewModelProvider(this).get(FolderViewModel.class);

        mFolderViewModel.getAllWords().observe(this, new Observer<List<Folder>>() {
            @Override
            public void onChanged(@Nullable final List<Folder> folders) {
                // Update the cached copy of the words in the adapter.
                folderAdapter.setData(folders);
            }
        });

        folderAdapter = new FolderAdapter(new FolderAdapter.IClickItemFolder() {
            @Override
            public void updateFolder(Folder folder) {
                clickUpdateFolder(folder);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rcv_list.setLayoutManager(linearLayoutManager);
        rcv_list.setHasFixedSize(true);

        rcv_list.setAdapter(folderAdapter);

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void clickUpdateFolder(Folder folder) {
        Intent intent = new Intent(MainActivity.this, EditFolderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_folder", folder);
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT_ITEM_REQUEST);
    }

    private void initUI() {
        rcv_list = findViewById(R.id.rcv_list);
        tvAdd = findViewById(R.id.tv_add);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String titleAdd = data.getStringExtra("titleAddData");
                String contentAdd = data.getStringExtra("contentAddData");

                Folder folder = new Folder(titleAdd, contentAdd);

                mFolderViewModel.insert(folder);

                displayToast("Add Item Sucessfully");
            }
        }

        if (requestCode == EDIT_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                displayToast("Update Item Sucessfully");
            }
        }
    }

    private void addItem() {
        Intent intent = new Intent(MainActivity.this, AddFolderActivity.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        displayToast("Press one more back button to close app");
    }
}
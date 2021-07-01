package com.example.lesson6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv_list;
    private ItemAdapter itemAdapter;
    private TextView tvAdd;
    private List<Item> itemList;

    private static final int ADD_ITEM_REQUEST = 1;
    private static final int EDIT_ITEM_REQUEST = 2;

    private int positionItemUpdate = -1;

    private void initUI() {
        rcv_list = findViewById(R.id.rcv_list);
        tvAdd = findViewById(R.id.tv_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        itemAdapter = new ItemAdapter(itemList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rcv_list.setLayoutManager(linearLayoutManager);
        rcv_list.setHasFixedSize(true);

        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int position, String title, String content) {
                positionItemUpdate = position;
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("titleData1", title);
                intent.putExtra("contentData1", content);
                String positionItemUpdate1 = String.valueOf(positionItemUpdate);
                intent.putExtra("positionItem1", positionItemUpdate1);
                startActivityForResult(intent, EDIT_ITEM_REQUEST);
            }
        });

        itemAdapter.setData(getListRow());
        rcv_list.setAdapter(itemAdapter);

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String titleAdd = data.getStringExtra("titleAddData");
                String contentAdd = data.getStringExtra("contentAddData");

                Item item = new Item(titleAdd, contentAdd);
                itemList.add(item);
                itemAdapter.notifyItemInserted(itemList.size() - 1);
                rcv_list.scrollToPosition(itemList.size() - 1);
                displayToast("Add Item Sucessfully");
            }
        }

        if (requestCode == EDIT_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Item itemUpdate = itemList.get(positionItemUpdate);

                String titleUpdate = data.getStringExtra("titleUpdate");
                String contentUpdate = data.getStringExtra("contentUpdate");

                itemUpdate.setTitle(titleUpdate);
                itemUpdate.setContent(contentUpdate);

                itemAdapter.notifyItemChanged(positionItemUpdate);
                displayToast("Update Item Sucessfully");
            }
        }
    }

    private void addItem() {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST);
    }

    public List<Item> getListRow() {
        itemList = new ArrayList<>();
        itemList.add(new Item("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        itemList.add(new Item("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        itemList.add(new Item("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        itemList.add(new Item("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Tổng hợp tin tức thời sự", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));
        itemList.add(new Item("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"));
        itemList.add(new Item("Cảm hứng sáng tạo", "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."));

        return itemList;
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
package com.example.lesson8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imgAdd;

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList;

    DBManager dbManager;

    private static final int ADD_REQUEST_CODE = 1;
    private static final int EDIT_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        dbManager = new DBManager(this);

        adapter = new ContactAdapter(this);
        contactList = dbManager.getAllContacts();
        adapter.setContacts(contactList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });

        adapter.setClickListener(new ContactAdapter.ClickListener() {
            @Override
            public void onClick(Contact contact, int position) {
//                displayToast(String.valueOf(position));
//                displayToast(contact.toString());

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("contact_edit", contact);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerview);
        imgAdd = findViewById(R.id.img_add);
    }

    private Contact createContact(Intent data) {
        String strNameAdd = data.getStringExtra("contact_name_add");
        String strAddressAdd = data.getStringExtra("contact_address_add");
        String strPhoneAdd = data.getStringExtra("contact_phone_add");

        Contact contact = new Contact(strNameAdd, strAddressAdd, strPhoneAdd);
        return contact;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Contact contact = createContact(data);
                if (contact != null) {
                    dbManager.addContact(contact);
                }
                updateRecyclerView();
                displayToast("Add Contact Sucessfully");
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);

                hideKeyboard();
            }
        }

        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String strIdUpdate = data.getStringExtra("contact_id_edit");
                String strNameUpdate = data.getStringExtra("contact_name_edit");
                String strAddressUpdate = data.getStringExtra("contact_address_edit");
                String strPhoneUpdate = data.getStringExtra("contact_phone_edit");
                Contact contact = new Contact();
                contact.setmId(Integer.parseInt(strIdUpdate));
                contact.setmName(strNameUpdate);
                contact.setmAddress(strAddressUpdate);
                contact.setmPhone(strPhoneUpdate);

                int result = dbManager.updateContact(contact);
                if (result > 0) {
                    updateRecyclerView();
                }
                displayToast("Update Contact Sucessfully");
            }
        }
    }

    private void updateRecyclerView() {
        contactList.clear();
        contactList.addAll(dbManager.getAllContacts());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
package com.example.lesson8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContacts(List<Contact> list) {
        this.contactList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if (contact == null) {
            return;
        }

        holder.tvName.setText(contact.getmName());
        holder.tvAddress.setText(contact.getmAddress());
        holder.tvPhone.setText(contact.getmPhone());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(context);

                int result = dbManager.deleteContact(contact.getmId());
                if (result > 0) {
                    displayToast("Delete Successfully");
                    contactList.remove(contact);
                    notifyDataSetChanged();

                    Intent intentEdit = new Intent(context, MainActivity.class);
                    Bundle bundle = new Bundle();
                    boolean isDelete = true;
                    bundle.putBoolean("isDeleteBoolean", isDelete);
                    intentEdit.putExtras(bundle);
                    context.startActivity(intentEdit);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactList != null) {
            return contactList.size();
        } else {
            return 0;
        }
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhone;
        private ImageView imgDelete;

        public ContactViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }

    private void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

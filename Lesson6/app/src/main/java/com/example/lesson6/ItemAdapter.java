package com.example.lesson6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RowViewHolder> {

    private List<Item> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ItemAdapter(List<Item> itemList, Context context) {
        super();
        this.itemList = itemList;
        this.context = context;
    }

    public void setData(List<Item> list) {
        this.itemList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_item, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.RowViewHolder holder, int position) {
        Item item = itemList.get(position);
        if (item == null) {
            return;
        }
        holder.tvTitle.setText(item.getTitle());
        holder.tvContent.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTitle;
        private TextView tvContent;

        public RowViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClickListener(getLayoutPosition(), tvTitle.getText().toString(), tvContent.getText().toString());
        }
    }

    public interface OnItemClickListener {
        void onClickListener(int position, String title, String content);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

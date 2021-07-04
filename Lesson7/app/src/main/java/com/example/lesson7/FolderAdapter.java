package com.example.lesson7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.RowViewHolder> {

    private List<Folder> itemList;

    private IClickItemFolder iClickItemFolder;

    public interface IClickItemFolder {
        void updateFolder(Folder folder);
    }

    public FolderAdapter(IClickItemFolder iClickItemFolder) {
        this.iClickItemFolder = iClickItemFolder;
    }

    public void setData(List<Folder> list) {
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
    public void onBindViewHolder(@NonNull @NotNull FolderAdapter.RowViewHolder holder, int position) {
        Folder folder = itemList.get(position);
        if (folder == null) {
            return;
        }
        holder.tvTitle.setText(folder.getTitle());
        holder.tvContent.setText(folder.getContent());
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemFolder.updateFolder(folder);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvContent;
        private ConstraintLayout rowLayout;

        public RowViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            rowLayout = itemView.findViewById(R.id.row_layout);
        }
    }
}

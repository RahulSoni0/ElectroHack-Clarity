package com.rahulsoni0.clarity.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahulsoni0.clarity.R;
import com.rahulsoni0.clarity.cache.SavedListDatabase;
import com.rahulsoni0.clarity.cache.SavedListEntityModel;
import com.rahulsoni0.clarity.ui.activities.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class SavedResourcesAdapter extends RecyclerView.Adapter<SavedResourcesAdapter.ViewHolder> {
    List<SavedListEntityModel> Savedlist = new ArrayList<>();
    Context context;

    public SavedResourcesAdapter(List<SavedListEntityModel> savedlist, Context context) {
        Savedlist = savedlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.savedlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SavedListEntityModel it = Savedlist.get(position);
        holder.setData(it.getImageUrl(), it.getTitle(), it.getDesc());
    }

    @Override
    public int getItemCount() {
        return Savedlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView deleteBtn, itemImage, detailsBtn;
        private TextView titleTv, descTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn = itemView.findViewById(R.id.btnSavedItemDelete);
            itemImage = itemView.findViewById(R.id.ivSavedItemImage);
            titleTv = itemView.findViewById(R.id.tvSavedItemTitle);
            descTv = itemView.findViewById(R.id.tvSavedItemDescriptionHere);
            detailsBtn = itemView.findViewById(R.id.btnSavedItemReadMore);

            detailsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url", Savedlist.get(getAdapterPosition()).getDetailUrl());
                    intent.putExtra("from", "");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(context, "Redirecting.....", Toast.LENGTH_SHORT).show();
                }
            });


        }


        public void setData(String urlToImage, String title, String details) {
            titleTv.setText(title);
            descTv.setText(details);
            Glide.with(itemView.getContext()).load(urlToImage).into(itemImage);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SavedListDatabase db = SavedListDatabase.getInstance(itemView.getContext());
                    SavedListEntityModel s = Savedlist.get(getLayoutPosition());
                    Savedlist.remove(getLayoutPosition());
                    db.savedListDao().deleteData(s);
                    Toast.makeText(itemView.getContext(), "delete succesfull", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
        }
    }

}

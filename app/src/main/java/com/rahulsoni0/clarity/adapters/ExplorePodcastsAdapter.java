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
import com.rahulsoni0.clarity.models.ExploreModel;
import com.rahulsoni0.clarity.ui.activities.WebViewActivity;

import java.util.List;

public class ExplorePodcastsAdapter extends RecyclerView.Adapter<ExplorePodcastsAdapter.ExploreviewHolder> {

    List<ExploreModel> DataList;
    Context context;

    public ExplorePodcastsAdapter(List<ExploreModel> DataList, Context context) {
        this.DataList = DataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExploreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.explore_item, parent, false);
        return new ExploreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreviewHolder holder, int position) {
        ExploreModel item = DataList.get(position);
        holder.setData(item.getImageUrl(), item.getTitle(), item.getDesc());
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ExploreviewHolder extends RecyclerView.ViewHolder {

        private ImageView imageIv, readmoreIv;
        private TextView titleTv, descTv;

        public ExploreviewHolder(@NonNull View itemView) {
            super(itemView);

            imageIv = itemView.findViewById(R.id.ivItemImage);
            readmoreIv = itemView.findViewById(R.id.btnItemReadMore);
            titleTv = itemView.findViewById(R.id.tvItemTitle);
            descTv = itemView.findViewById(R.id.tvItemDescriptionHere);


//
            readmoreIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), WebViewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url", DataList.get(getAdapterPosition()).getDetailsUrl());
                    intent.putExtra("from", "podcasts");
                    itemView.getContext().startActivity(intent);
                    Toast.makeText(itemView.getContext(), "Redirecting.....", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(String imageUrl, String title, String desc) {
            titleTv.setText(title);
            descTv.setText(desc);
            Glide.with(context).load(imageUrl).into(imageIv);
        }
    }
}

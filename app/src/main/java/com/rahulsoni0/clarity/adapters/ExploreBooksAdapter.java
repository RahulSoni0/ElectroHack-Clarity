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
import com.rahulsoni0.clarity.models.ExploreModel;
import com.rahulsoni0.clarity.ui.activities.WebViewActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExploreBooksAdapter extends RecyclerView.Adapter<ExploreBooksAdapter.ExploreviewHolder> {

    List<ExploreModel> DataList;
    List<SavedListEntityModel> savedlist = new ArrayList<>();
    Context context;

    public ExploreBooksAdapter(List<ExploreModel> dataList, List<SavedListEntityModel> savedlist, Context context) {
        DataList = dataList;
        this.savedlist = savedlist;
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
        holder.setData(item.getImageUrl(), item.getTitle(), item.getDesc(), item);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class ExploreviewHolder extends RecyclerView.ViewHolder {

        private ImageView imageIv, readmoreIv, likedBtn;
        private TextView titleTv, descTv;

        public ExploreviewHolder(@NonNull View itemView) {
            super(itemView);

            imageIv = itemView.findViewById(R.id.ivItemImage);
            readmoreIv = itemView.findViewById(R.id.btnItemReadMore);
            titleTv = itemView.findViewById(R.id.tvItemTitle);
            descTv = itemView.findViewById(R.id.tvItemDescriptionHere);
            likedBtn = itemView.findViewById(R.id.btnItemLiked);


//
            readmoreIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), WebViewActivity.class);
                    intent.putExtra("url", DataList.get(getAdapterPosition()).getDetailsUrl());
                    intent.putExtra("from", "books");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(intent);

                    Toast.makeText(itemView.getContext(), "Redirecting.....", Toast.LENGTH_SHORT).show();
                }
            });
            likedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    likedBtn.setImageResource(R.drawable.red_heart);
                    addSavedList(DataList.get(getAdapterPosition()));
                }
            });
        }

        public void setData(String imageUrl, String title, String desc, ExploreModel item) {
            titleTv.setText(title);
            descTv.setText(desc);
            Glide.with(context).load(imageUrl).into(imageIv);
            SavedListEntityModel s = new SavedListEntityModel(item.getTitle(), item.getDesc(), item.getImageUrl(), item.getDetailsUrl());
            Set<String> titles = new HashSet<>();
            for (SavedListEntityModel sss : savedlist) {
                titles.add(sss.getTitle());
            }
            if (titles.contains(s.getTitle())) {
                Glide.with(itemView.getContext()).load(R.drawable.red_heart).into(likedBtn);
            }
        }

        public void addSavedList(ExploreModel articleModel) {
//            binding.btnItemLiked.setImageResource(R.drawable.red_heart);
            Glide.with(context).load(R.drawable.red_heart).into(likedBtn);
            SavedListDatabase db = SavedListDatabase.getInstance(itemView.getContext());
            SavedListEntityModel s = new SavedListEntityModel(articleModel.getTitle(), articleModel.getDesc(), articleModel.getImageUrl(), articleModel.getDetailsUrl());

            Set<String> titles = new HashSet<>();
            for (SavedListEntityModel sss : savedlist) {
                titles.add(sss.getTitle());
            }
            if (titles.contains(s.getTitle())) {
                Toast.makeText(itemView.getContext(), "Already present", Toast.LENGTH_SHORT).show();
            } else {
                db.savedListDao().insertSavedData(s);
                savedlist.add(s);
                Toast.makeText(itemView.getContext(), "Saved ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

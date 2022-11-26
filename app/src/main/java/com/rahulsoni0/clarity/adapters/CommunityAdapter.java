package com.rahulsoni0.clarity.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsoni0.clarity.R;
import com.rahulsoni0.clarity.ui.activities.ChatActivity;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.viewHolder> {

    List<String> s;
    Context context;
    String userName;

    public CommunityAdapter(List<String> s, Context context, String UserName) {
        this.s = s;
        this.context = context;
        this.userName = UserName;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_item, parent, false);
        return new CommunityAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.name.setText(s.get(position));


    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvItemCommunityName);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), ChatActivity.class);
                    i.putExtra("selected_topic", s.get(getAdapterPosition()));
                    i.putExtra("user_name", userName);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(i);
                }
            });


        }
    }
}

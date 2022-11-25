package com.rahulsoni0.clarity.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsoni0.clarity.adapters.SavedResourcesAdapter;
import com.rahulsoni0.clarity.cache.SavedListDatabase;
import com.rahulsoni0.clarity.cache.SavedListEntityModel;
import com.rahulsoni0.clarity.databinding.ActivitySavedListBinding;

import java.util.ArrayList;
import java.util.List;

public class SavedListActivity extends AppCompatActivity {
    private ActivitySavedListBinding binding;
    List<SavedListEntityModel> savedlist = new ArrayList<>();
    SavedResourcesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SavedListDatabase db = SavedListDatabase.getInstance(this.getApplicationContext());
        List<SavedListEntityModel> savednews = db.savedListDao().getAlldata();
        //Toast.makeText(this, ""+ savednews.toString(), Toast.LENGTH_SHORT).show();
        if (!savednews.isEmpty()) {
            savedlist.addAll(savednews);
            binding.ivEmptyDataImage.setVisibility(View.GONE);
            binding.tvEmptyDataText.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "No saved news", Toast.LENGTH_SHORT).show();
            binding.ivEmptyDataImage.setVisibility(View.VISIBLE);
            binding.tvEmptyDataText.setVisibility(View.VISIBLE);

        }
        initRv();


    }

    private void initRv() {
        adapter = new SavedResourcesAdapter(savedlist, this);
        binding.rvSavedList.setAdapter(adapter);
        binding.rvSavedList.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
        adapter.notifyDataSetChanged();


    }
}
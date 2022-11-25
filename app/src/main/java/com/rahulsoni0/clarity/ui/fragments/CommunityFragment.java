package com.rahulsoni0.clarity.ui.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rahulsoni0.clarity.adapters.CommunityAdapter;
import com.rahulsoni0.clarity.databinding.FragmentCommunityBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class CommunityFragment extends Fragment {

    private FragmentCommunityBinding binding;
    CommunityAdapter adapter;
    List<String> communityName = new ArrayList<>();
    private DatabaseReference dbref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCommunityBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initRv();
        initData();


    }

    private void initData() {

        dbref = FirebaseDatabase.getInstance().getReference().getRoot();
        Log.d("@@@@", "initData: " + dbref.toString());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                communityName.addAll(set);
                Log.d("@@@@", "onDataChange: " + set.toString());
                initRv();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initRv() {
        adapter = new CommunityAdapter(communityName, getContext(), " Rahul Soni ");
        binding.rvTopics.setAdapter(adapter);
        binding.rvTopics.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }
}
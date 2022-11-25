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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rahulsoni0.clarity.adapters.ExploreArticlesAdapter;
import com.rahulsoni0.clarity.adapters.ExploreBooksAdapter;
import com.rahulsoni0.clarity.adapters.ExplorePodcastsAdapter;
import com.rahulsoni0.clarity.databinding.FragmentExploreBinding;
import com.rahulsoni0.clarity.models.ExploreModel;

import java.util.ArrayList;
import java.util.List;


public class ExploreFragment extends Fragment {


    private FragmentExploreBinding binding;
    ExploreBooksAdapter BooksAdapter;
    ExplorePodcastsAdapter PodcastsAdapter;
    ExploreArticlesAdapter ArticlesAdapter;
    List<ExploreModel> ArticlesDataList = new ArrayList<>();
    List<ExploreModel> PodcastsDataList = new ArrayList<>();
    List<ExploreModel> BooksDataList = new ArrayList<>();
    FirebaseFirestore firestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore = FirebaseFirestore.getInstance();
        initRvArticles();
        initRvBooks();
        initRvPodcasts();
        initData();


    }

    private void initData() {

//        ExploreModel exp = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp1 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp2 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp3 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp4 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp5 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp6 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        ExploreModel exp7 = new ExploreModel("Calm your mind ", "learn varioud pratices to calm your mid", "https://cdn.vectorstock.com/i/1000x1000/52/86/black-peace-symbol-created-in-grunge-style-vector-27075286.webp", "https://example.com");
//        DataList.add(exp);
//        DataList.add(exp1);
//        DataList.add(exp2);
//        DataList.add(exp3);
//        DataList.add(exp4);
//        DataList.add(exp5);
//        DataList.add(exp6);
//        DataList.add(exp7);

        firestore.collection("articles").document("yqs8AVpjQfAHa37B16h0").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> s1 = (List<String>) documentSnapshot.get("1");
                    List<String> s2 = (List<String>) documentSnapshot.get("2");
                    List<String> s3 = (List<String>) documentSnapshot.get("3");
                    List<String> s4 = (List<String>) documentSnapshot.get("4");
                    List<String> s5 = (List<String>) documentSnapshot.get("5");
                    ExploreModel temp1 = new ExploreModel(s1.get(0), s1.get(1), s1.get(2), s1.get(3));
                    ExploreModel temp2 = new ExploreModel(s2.get(0), s2.get(1), s2.get(2), s2.get(3));
                    ExploreModel temp3 = new ExploreModel(s3.get(0), s3.get(1), s3.get(2), s3.get(3));
                    ExploreModel temp4 = new ExploreModel(s4.get(0), s4.get(1), s4.get(2), s4.get(3));
                    ExploreModel temp5 = new ExploreModel(s5.get(0), s5.get(1), s5.get(2), s5.get(3));
                    ArticlesDataList.add(temp1);
                    ArticlesDataList.add(temp2);
                    ArticlesDataList.add(temp3);
                    ArticlesDataList.add(temp4);
                    ArticlesDataList.add(temp5);
                    initRvArticles();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });
        firestore.collection("books").document("33PHRAiiPXgCOV4zbqYe").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> s1 = (List<String>) documentSnapshot.get("1");
                    List<String> s2 = (List<String>) documentSnapshot.get("2");
                    List<String> s3 = (List<String>) documentSnapshot.get("3");
                    List<String> s4 = (List<String>) documentSnapshot.get("4");

                    ExploreModel temp1 = new ExploreModel(s1.get(0), s1.get(1), s1.get(2), s1.get(3));
                    ExploreModel temp2 = new ExploreModel(s2.get(0), s2.get(1), s2.get(2), s2.get(3));
                    ExploreModel temp3 = new ExploreModel(s3.get(0), s3.get(1), s3.get(2), s3.get(3));
                    ExploreModel temp4 = new ExploreModel(s4.get(0), s4.get(1), s4.get(2), s4.get(3));

                    BooksDataList.add(temp1);
                    BooksDataList.add(temp2);
                    BooksDataList.add(temp3);
                    BooksDataList.add(temp4);

                    initRvBooks();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });
        firestore.collection("podcasts").document("BrrMvzzy5OcfFk6Lg4rm").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> s1 = (List<String>) documentSnapshot.get("1");
                    List<String> s2 = (List<String>) documentSnapshot.get("2");
                    List<String> s3 = (List<String>) documentSnapshot.get("3");
                    List<String> s4 = (List<String>) documentSnapshot.get("4");
                    ExploreModel temp1 = new ExploreModel(s1.get(0), s1.get(1), s1.get(2), s1.get(3));
                    ExploreModel temp2 = new ExploreModel(s2.get(0), s2.get(1), s2.get(2), s2.get(3));
                    ExploreModel temp3 = new ExploreModel(s3.get(0), s3.get(1), s3.get(2), s3.get(3));
                    ExploreModel temp4 = new ExploreModel(s4.get(0), s4.get(1), s4.get(2), s4.get(3));
                    PodcastsDataList.add(temp1);
                    PodcastsDataList.add(temp2);
                    PodcastsDataList.add(temp3);
                    PodcastsDataList.add(temp4);

                    initRvPodcasts();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });


    }

    private void initRvArticles() {
        ArticlesAdapter = new ExploreArticlesAdapter(ArticlesDataList, getActivity().getBaseContext());
        binding.rvArticles.setAdapter(ArticlesAdapter);
        binding.rvArticles.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        ArticlesAdapter.notifyDataSetChanged();

    }

    private void initRvPodcasts() {
        PodcastsAdapter = new ExplorePodcastsAdapter(PodcastsDataList, getActivity().getBaseContext());
        binding.rvPodcasts.setAdapter(PodcastsAdapter);
        binding.rvPodcasts.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        PodcastsAdapter.notifyDataSetChanged();

    }

    private void initRvBooks() {
        BooksAdapter = new ExploreBooksAdapter(BooksDataList, getActivity().getBaseContext());
        binding.rvBooks.setAdapter(BooksAdapter);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        BooksAdapter.notifyDataSetChanged();

    }
}
package com.rahulsoni0.clarity.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rahulsoni0.clarity.adapters.LofiAdapter;
import com.rahulsoni0.clarity.adapters.homePosterAdapter;
import com.rahulsoni0.clarity.cache.SavedListDatabase;
import com.rahulsoni0.clarity.cache.SavedListEntityModel;
import com.rahulsoni0.clarity.databinding.FragmentHomeBinding;
import com.rahulsoni0.clarity.models.ExploreModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    ArrayList<String> homePosterList = new ArrayList<>();
    ArrayList<String> arrangedPosterList = new ArrayList<>();
    homePosterAdapter posterAdapter;

    LofiAdapter lofiAdapter;
    List<ExploreModel> StudyDataList = new ArrayList<>();
    List<ExploreModel> SleepsDataList = new ArrayList<>();
    List<ExploreModel> RelaxDataList = new ArrayList<>();
    List<SavedListEntityModel> savedData = new ArrayList<>();
    FirebaseFirestore firestore;


    int currentPage;
    final static int DELAY_TIME = 1500;
    final static int PERIOD_TIME = 1500;
    Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //hardcoded url's for sliders
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/clarity-ddffb.appspot.com/o/1.png?alt=media&token=49da152d-c55e-4d78-a865-11658d79babc");
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/clarity-ddffb.appspot.com/o/2.png?alt=media&token=8e1402c6-dc20-45b3-b83a-b7316b930fbf");
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/clarity-ddffb.appspot.com/o/3.png?alt=media&token=f0191417-29fb-4f50-8b72-7d0b5829e31c");
        //end
        setPosters(homePosterList); //calling poster slider function

        firestore = FirebaseFirestore.getInstance();
        SavedListDatabase db = SavedListDatabase.getInstance(getContext());
        List<SavedListEntityModel> sn = db.savedListDao().getAlldata();
        savedData.addAll(sn);
        initRvStudy();
        initRvRelax();
        initRvSleep();
        initData();

    }

    private void initRvSleep() {
        lofiAdapter = new LofiAdapter(SleepsDataList, savedData, getContext());
        binding.rvSleep.setAdapter(lofiAdapter);
        binding.rvSleep.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        lofiAdapter.notifyDataSetChanged();
    }

    private void initRvRelax() {
        lofiAdapter = new LofiAdapter(RelaxDataList, savedData, getContext());
        binding.rvRelax.setAdapter(lofiAdapter);
        binding.rvRelax.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        lofiAdapter.notifyDataSetChanged();
    }

    private void initRvStudy() {
        lofiAdapter = new LofiAdapter(StudyDataList, savedData, getContext());
        binding.rvStudy.setAdapter(lofiAdapter);
        binding.rvStudy.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        lofiAdapter.notifyDataSetChanged();
    }

    private void setPosters(ArrayList<String> l) {
        if (timer != null) {
            timer.cancel();

        }

        currentPage = 1;  //-->first url of main list
        //applying logic to make sliders infinite so modifying list
        for (int i = 0; i < l.size(); i++) {
            arrangedPosterList.add(l.get(i));
        }

        arrangedPosterList.add(0, l.get(l.size() - 1));
        arrangedPosterList.add(l.get(0));

        //setting Adapter for poster Slider
        posterAdapter = new homePosterAdapter(arrangedPosterList);
        binding.vpHomeBanner.setAdapter(posterAdapter);
        binding.vpHomeBanner.setPageMargin(56);

        binding.vpHomeBanner.setCurrentItem(currentPage);
        //end

        autoSliding(arrangedPosterList);

        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_IDLE) {

                    loopingInfinitely(arrangedPosterList);

                }

            }
        };

        binding.vpHomeBanner.addOnPageChangeListener(listener);
        binding.vpHomeBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                loopingInfinitely(arrangedPosterList);
                stopSlidingOnTouch();

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    autoSliding(arrangedPosterList);

                }
                return false;
            }
        });

    }

    private void loopingInfinitely(ArrayList<String> l) {

        if (currentPage == l.size() - 1) {

            currentPage = 1;
            binding.vpHomeBanner.setCurrentItem(currentPage, false);

        } else if (currentPage == 0) {
            currentPage = l.size() - 2;
            binding.vpHomeBanner.setCurrentItem(currentPage, false);
        }

    }

    private void autoSliding(ArrayList<String> l) {

        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {

                if (currentPage >= l.size()) {

                    currentPage = 0;

                }

                binding.vpHomeBanner.setCurrentItem(currentPage++, true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(run);

            }
        }, DELAY_TIME, PERIOD_TIME);

    }

    private void stopSlidingOnTouch() {

        timer.cancel();


    }

    public void initData() {
        firestore.collection("study").document("9nccmui1cSuwPxq1qsMJ").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
                    StudyDataList.add(temp1);
                    StudyDataList.add(temp2);
                    StudyDataList.add(temp3);
                    StudyDataList.add(temp4);
                    StudyDataList.add(temp5);
                    initRvStudy();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });
        firestore.collection("relax").document("tE1r8qHhQlaYziRqrCeT").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> s1 = (List<String>) documentSnapshot.get("1");
                    List<String> s2 = (List<String>) documentSnapshot.get("2");


                    ExploreModel temp1 = new ExploreModel(s1.get(0), s1.get(1), s1.get(2), s1.get(3));
                    ExploreModel temp2 = new ExploreModel(s2.get(0), s2.get(1), s2.get(2), s2.get(3));

                    RelaxDataList.add(temp1);
                    RelaxDataList.add(temp2);


                    initRvRelax();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });
        firestore.collection("sleep").document("ZmHL1x4FwrmwRFAZomBh").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
                    SleepsDataList.add(temp1);
                    SleepsDataList.add(temp2);
                    SleepsDataList.add(temp3);
                    SleepsDataList.add(temp4);

                    initRvSleep();
                    Log.d("####", "onSuccess: " + s1.toString());
                } else {

                }
            }
        });
    }
}
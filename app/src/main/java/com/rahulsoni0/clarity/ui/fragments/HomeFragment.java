package com.rahulsoni0.clarity.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.rahulsoni0.clarity.R;
import com.rahulsoni0.clarity.adapters.homePosterAdapter;
import com.rahulsoni0.clarity.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
   private FragmentHomeBinding binding;
    ArrayList<String> homePosterList = new ArrayList<>();
    ArrayList<String> arrangedPosterList = new ArrayList<>();
    homePosterAdapter posterAdapter;
    int currentPage;
    final static int DELAY_TIME = 1500;
    final static int PERIOD_TIME = 2000;
    Timer timer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //hardcoded url's for sliders
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/sociaqr.appspot.com/o/1.png?alt=media&token=d9de33ac-c158-4fe7-ae75-71ec1f7435af");
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/sociaqr.appspot.com/o/2.png?alt=media&token=065eb590-d6ed-4099-9a24-4be522f7dd72");
        homePosterList.add("https://firebasestorage.googleapis.com/v0/b/sociaqr.appspot.com/o/3.png?alt=media&token=860395e8-26bf-4014-94aa-ddcfcc72a5a3");
        //end
        setPosters(homePosterList); //calling poster slider function

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
}
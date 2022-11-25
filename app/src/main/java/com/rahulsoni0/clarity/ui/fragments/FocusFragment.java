package com.rahulsoni0.clarity.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahulsoni0.clarity.databinding.FragmentFocusBinding;
import com.rahulsoni0.clarity.utils.Storage;
import com.view.circulartimerview.CircularTimerListener;
import com.view.circulartimerview.TimeFormatEnum;

public class FocusFragment extends Fragment {
    private FragmentFocusBinding binding;
    Storage storage;
    int focusCount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFocusBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storage = new Storage(getContext().getApplicationContext());

        int oldCount  = storage.getfocusCount();
        String s = "Total Focus rounds : " + oldCount;
        binding.tvFocusCount.setText(s);

        binding.confetti.setVisibility(View.GONE);
        binding.progressCircular.setProgress(0f);
        binding.progressCircular.setMaxValue(100);
        binding.btnResetFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage.setFocusCount(0);
                binding.tvFocusCount.setText("Total Focus rounds : " + 0);
            }
        });

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.confetti.setVisibility(View.GONE);

                binding.progressCircular.startTimer();
            }
        });
        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressCircular.stopTimer();
                binding.progressCircular.setProgress(0f);
                binding.progressCircular.setText("");
                binding.confetti.setVisibility(View.GONE);
                Toast.makeText(getActivity().getBaseContext(), "stopped", Toast.LENGTH_SHORT).show();
            }
        });


        binding.progressCircular.setCircularTimerListener(new CircularTimerListener() {
            @Nullable
            @Override
            public String updateDataOnTick(long l) {
                String s = "Remaining "
                        + (int) Math.ceil((l / 1000.f)) + " sec";
                return s;
            }

            @Override
            public void onTimerFinished() {
                Toast.makeText(getActivity().getBaseContext(), "FINISHED", Toast.LENGTH_SHORT).show();
                binding.progressCircular.setPrefix("");
                binding.progressCircular.setSuffix("");
                binding.progressCircular.setText(" Yay!! Completed");
                binding.confetti.setVisibility(View.VISIBLE);
                ++focusCount;
                storage.setFocusCount(focusCount);
                binding.tvFocusCount.setText("Total Focus rounds : " + (oldCount+focusCount)+"");

            }
        }, 10, TimeFormatEnum.SECONDS, 10);


    }

}
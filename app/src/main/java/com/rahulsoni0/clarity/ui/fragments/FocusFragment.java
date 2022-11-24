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
import com.view.circulartimerview.CircularTimerListener;
import com.view.circulartimerview.TimeFormatEnum;

public class FocusFragment extends Fragment {
    private FragmentFocusBinding binding;

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

        binding.confetti.setVisibility(View.GONE);
        binding.progressCircular.setProgress(0f);
        binding.progressCircular.setMaxValue(100);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                binding.progressCircular.setText("FINISHED THANKS!");
                binding.confetti.setVisibility(View.VISIBLE);

            }
        }, 10, TimeFormatEnum.SECONDS, 10);


    }

}
package com.rahulsoni0.clarity.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahulsoni0.clarity.databinding.FragmentCommunityBinding;


public class CommunityFragment extends Fragment {

    private FragmentCommunityBinding binding;

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

        binding.btnJoinRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String text = binding.tvRoomName.getText().toString();
//
//                if (text.length() > 0) {
//
//                    try {
//                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
//                                .setServerURL(new URL("https://meet.jit.si"))
//                                .setRoom(text)
//                                .setConfigOverride("requireDisplayName", false)
//                                .build();
//                        JitsiMeetActivity.launch(getActivity().getBaseContext(), options);
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//
//                    Toast.makeText(getContext(), "Please enter a meeting id", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}
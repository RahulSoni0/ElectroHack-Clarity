package com.rahulsoni0.clarity.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.rahulsoni0.clarity.databinding.FragmentSettingsBinding;
import com.rahulsoni0.clarity.onboarding.auth.AuthActivity;
import com.rahulsoni0.clarity.ui.activities.SavedListActivity;
import com.rahulsoni0.clarity.utils.Storage;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    Storage storage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storage = new Storage(getContext().getApplicationContext());

        binding.edtName.setVisibility(View.GONE);
        binding.edtAge.setVisibility(View.GONE);
        binding.edtGender.setVisibility(View.GONE);
        binding.btnManageDetails.setVisibility(View.GONE);

        binding.btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = storage.getName();
                String age = storage.getAge();
                String gender = storage.getGender();
                binding.edtName.setVisibility(View.VISIBLE);
                binding.edtAge.setVisibility(View.VISIBLE);
                binding.edtGender.setVisibility(View.VISIBLE);
                binding.btnManageDetails.setVisibility(View.VISIBLE);

                if (!name.equals("") && !gender.equals("") && !age.equals("")) {
                    binding.edtName.setText(name);
                    binding.edtAge.setText(age);
                    binding.edtGender.setText(gender);
                }


            }
        });

        binding.btnManageDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.edtName.getText().toString().trim();
                String age = binding.edtAge.getText().toString().trim();
                String gender = binding.edtGender.getText().toString().trim();
                if (!name.equals("") && !gender.equals("") && !age.equals("")) {
                    storage.setName(name);
                    storage.setAge(age);
                    storage.setGender(gender);
                }
                Toast.makeText(getActivity().getBaseContext(), "saved changes", Toast.LENGTH_SHORT).show();

                binding.edtName.setVisibility(View.GONE);
                binding.edtAge.setVisibility(View.GONE);
                binding.edtGender.setVisibility(View.GONE);
                binding.btnManageDetails.setVisibility(View.GONE);

            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity().getBaseContext(), AuthActivity.class);
                startActivity(i);
                Toast.makeText(getActivity().getBaseContext(), "Logged out Successfull", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        });

        binding.btnSavedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getBaseContext(), SavedListActivity.class);
                startActivity(i);
            }
        });
    }
}
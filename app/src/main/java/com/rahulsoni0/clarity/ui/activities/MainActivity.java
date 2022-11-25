package com.rahulsoni0.clarity.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rahulsoni0.clarity.R;
import com.rahulsoni0.clarity.databinding.ActivityMainBinding;
import com.rahulsoni0.clarity.ui.fragments.CommunityFragment;
import com.rahulsoni0.clarity.ui.fragments.ExploreFragment;
import com.rahulsoni0.clarity.ui.fragments.FocusFragment;
import com.rahulsoni0.clarity.ui.fragments.HomeFragment;
import com.rahulsoni0.clarity.ui.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String extraStr = extras.getString("message_key");
            if (extraStr.equals("str")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(binding.fragContainerNav.getId(),
                                new ExploreFragment()).commit();
                binding.bottomNavBar.setItemSelected(R.id.explore, true);
            }
        }
        if (savedInstanceState == null) { // setting default as Home Frag..
            binding.bottomNavBar.setItemSelected(R.id.home, true);
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragContainerNav.getId(),
                            new HomeFragment()).commit();

        }

        bottomMenu();// bottomNav Manager

    }

    private void bottomMenu() {
        binding.bottomNavBar.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i) {
                            case R.id.home:
                                fragment = new HomeFragment();
                                break;
                            case R.id.explore:
                                fragment = new ExploreFragment();
                                break;
                            case R.id.focus:
                                fragment = new FocusFragment();
                                break;
                            case R.id.community:
                                fragment = new CommunityFragment();
                                break;
                            case R.id.settings:
                                fragment = new SettingsFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction()
                                .replace(binding.fragContainerNav.getId(),
                                        fragment).commit();

                    }
                });
    }

    void changeFrag(Fragment fragment) {
        FragmentTransaction fragmentTransaction0 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction0.replace(binding.fragContainerNav.getId(), fragment, "");
        fragmentTransaction0.commit();

    }
}
package com.example.recycleview_drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RoomFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_room, container, false);
        HomeFragment home_Fragment = new HomeFragment();

        replaceFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    // Thay thế bằng Fragment tương ứng, ví dụ:
                    replaceFragment(new HomeFragment());
                    return true;
                case R.id.shorts:
                    // Thay thế bằng Fragment tương ứng, ví dụ:
                    replaceFragment(new ShortsFragment());
                    return true;
                case R.id.voice:
                    replaceFragment(new VoiceFragment());
                    return true;
                case R.id.setting:
                    replaceFragment(new SettingFragment());
                    return true;
                default:
                    return false;
            }
        });


        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null); // Để quay lại Fragment trước đó khi nhấn nút Back
        transaction.commit();
    }

}
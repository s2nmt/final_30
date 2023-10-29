package com.example.recycleview_drawer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewpage extends FragmentStateAdapter {

    public viewpage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new LivingFragment();
            case 1:
                return new KitchenFragment();

            case 2:
                return new BedroomFragment();
            default:
                return new DiningFragment();
        }

    }


    @Override
    public int getItemCount() {
        return 4;
    }
}

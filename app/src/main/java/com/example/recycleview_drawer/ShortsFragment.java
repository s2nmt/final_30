package com.example.recycleview_drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ShortsFragment extends Fragment {

    private TabLayout mTablayout;
    private ViewPager2 mviewPager;
    private viewpage viewpage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shorts, container, false);
        mTablayout = (TabLayout) view.findViewById(R.id.tablayout);
        mviewPager = (ViewPager2) view.findViewById(R.id.view_page);
        viewpage viewpage = new viewpage(getActivity());
        mviewPager.setAdapter(viewpage);

        new TabLayoutMediator(mTablayout, mviewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Living");
                    break;
                case 1:
                    tab.setText("Kitchen");
                    break;
                case 2:
                    tab.setText("Bed");
                    break;
                case 3:
                    tab.setText("Dining");
                    break;
            }
        }).attach();

        return view;
    }
}
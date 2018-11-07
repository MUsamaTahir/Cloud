package com.example.usama.cloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DiaryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentActivity mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayoutid);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPagerAdapter adapter= new viewPagerAdapter(getFragmentManager());

        adapter.AddFragments(new FragmentWithInCity(),"Within city");
        adapter.AddFragments(new FragmentOutOfCity(),"Out Of city");
        adapter.AddFragments(new FragmentOutOfState(),"Out Of State");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

}

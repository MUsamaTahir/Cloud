package com.example.usama.cloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOutOfCity extends Fragment {
   View view;
    public FragmentOutOfCity() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_out_of_city,container,false);
        return view;
    }
}

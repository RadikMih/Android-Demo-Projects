package com.fragmentsdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";

    private Button navFrag1Button;
    private Button navFrag2Button;
    private Button navFrag3Button;
    private Button navActivity2Button;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        navFrag1Button = view.findViewById(R.id.navFrag1Button);
        navFrag2Button = view.findViewById(R.id.navFrag2Button);
        navFrag3Button = view.findViewById(R.id.navFrag3Button);
        navActivity2Button = view.findViewById(R.id.navActivity2);

        


        return view;
    }
}

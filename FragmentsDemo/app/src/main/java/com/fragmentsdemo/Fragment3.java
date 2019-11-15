package com.fragmentsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private static final String TAG = "Fragment3";

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

        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        navFrag1Button = view.findViewById(R.id.navFrag1Button);
        navFrag2Button = view.findViewById(R.id.navFrag2Button);
        navFrag3Button = view.findViewById(R.id.navFrag3Button);
        navActivity2Button = view.findViewById(R.id.navActivity2);

        Log.d(TAG, "onCreateView : started");

        navFrag1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Fragment 1", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(0);
            }
        });

        navFrag2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Fragment 2", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(1);
            }
        });

        navFrag3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Fragment 3", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(2);
            }
        });

        navActivity2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Going to Activity 2", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}

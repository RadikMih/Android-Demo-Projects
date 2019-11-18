package com.actionbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button buttonTest;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        buttonTest = view.findViewById(R.id.buttonTest3);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Testing button click 3", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

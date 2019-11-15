package com.e.helloworld.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.helloworld.R;


class RecyclerCellHolder extends RecyclerView.ViewHolder {

    TextView firstNameTextView;
    TextView lastNameTextView;
    ImageView userImageView;


    RecyclerCellHolder(@NonNull View itemView) {
        super(itemView);

        firstNameTextView = itemView.findViewById(R.id.fistnameTextView);
        lastNameTextView = itemView.findViewById(R.id.lastnameTextView);
        userImageView = itemView.findViewById(R.id.userImageView);
    }


}

package com.e.helloworld.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.helloworld.R;
import com.e.helloworld.data.User;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerCellHolder> {

    private List<User> users;

    public RecyclerAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerCellHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.recycler_cell, parent, false);
        RecyclerCellHolder holder = new RecyclerCellHolder(userView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCellHolder holder, int position) {
        User user = users.get(position);

        TextView firstName = holder.firstNameTextView;
        firstName.setText(user.getFirstName());

        TextView lastName = holder.lastNameTextView;
        lastName.setText(user.getLastName());

        ImageView userImage = holder.userImageView;
        userImage.setImageResource(user.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

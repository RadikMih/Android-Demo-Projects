package com.e.helloworld.data;

import com.e.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public final List<User> users = new ArrayList<>();


    public Data() {

        users.add(new User("John 1", "Doe 1", R.drawable.motor8));
        users.add(new User("John 2", "Doe 2", R.drawable.motor2));
        users.add(new User("John 3", "Doe 3", R.drawable.motor3));
        users.add(new User("John 4", "Doe 4", R.drawable.motor4));
        users.add(new User("John 5", "Doe 5", R.drawable.motor5));
        users.add(new User("John 6", "Doe 6", R.drawable.i8));
        users.add(new User("John 7", "Doe 7", R.drawable.motor7));
        users.add(new User("John 8", "Doe 8", R.drawable.motor6));

    }


}

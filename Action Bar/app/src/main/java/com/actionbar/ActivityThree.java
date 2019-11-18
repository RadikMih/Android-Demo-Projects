package com.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        TextView title = findViewById(R.id.activityTitle3);
        title.setText("This is Activity Three");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem item = menu.getItem(3);
        item.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_android:
                        Intent intent0 = new Intent(ActivityThree.this, MainActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.ic_camera:
                        Intent intent1 = new Intent(ActivityThree.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_car:
                        Intent intent2 = new Intent(ActivityThree.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_security:

                        break;
                    case R.id.ic_star:
                        Intent intent4 = new Intent(ActivityThree.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }
}

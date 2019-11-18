package com.actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityFour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        TextView title = findViewById(R.id.activityTitle4);
        title.setText("This is Activity Four");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem item = menu.getItem(4);
        item.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_android:
                        Intent intent0 = new Intent(ActivityFour.this, MainActivity.class);
                        startActivity(intent0);
                        break;
                    case R.id.ic_camera:
                        Intent intent1 = new Intent(ActivityFour.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_car:
                        Intent intent2 = new Intent(ActivityFour.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_security:
                        Intent intent3 = new Intent(ActivityFour.this, ActivityThree.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_star:

                        break;
                }

                return false;
            }
        });
    }
}

package com.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFunction(View view) {
        Log.i("Test", "Button is clicked");
        ImageView imageView = findViewById(R.id.car);
        imageView.setImageResource(R.drawable.maxresdefault);
//        EditText enterNameField = findViewById(R.id.enterNameField);
//        EditText usernameField = findViewById(R.id.UsernameField);
//        EditText userPassword = findViewById(R.id.UserPassword);
//        Toast.makeText(MainActivity.this, usernameField.getText().toString(), Toast.LENGTH_LONG).show();
//        Log.i(usernameField.getText().toString(), userPassword.getText().toString());
        //Log.i("Info", enterNameField.getText().toString());

    }

//    public void newClickFunction(View view) {
//        Log.i("Info", "New Button Clicked");
//    }

    public void convertCurrency(View view) {
        Log.i("Test", "Convert button is clicked");
        EditText currencyInput = findViewById(R.id.DecimalInputView);
        double currency = convertMoney(Double.parseDouble(currencyInput.getText().toString()));
        Toast.makeText(MainActivity.this, String.format("BGN %.2f", currency), Toast.LENGTH_LONG).show();
    }

    public double convertMoney(double amount) {
        return amount * 2.0;
    }

    public void fade(View view) {
        ImageView image = findViewById(R.id.car);
        ImageView car2 = findViewById(R.id.car2);

        image.animate().alpha(0f).setDuration(2000);
        car2.animate().alpha(1f).setDuration(2000);
    }

}

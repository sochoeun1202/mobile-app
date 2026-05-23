package com.sochoeun.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare Variables
    TextView tvFoodItems;
    ImageView imageView;

    Button btnFruit;
    Button btnVegetable;
    Button btnDrink;
    ImageButton btnCall;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Variables
        tvFoodItems = findViewById(R.id.tvFoodItems);
        imageView = findViewById(R.id.imageView);

        btnFruit = findViewById(R.id.btnFruit);
        btnVegetable = findViewById(R.id.btnVegetable);
        btnDrink = findViewById(R.id.btnDrink);

        btnCall = findViewById(R.id.imgButtonCall);
        btnSearch = findViewById(R.id.imgButtonSearch);
    }

    // Handler
    public void btnClickCall(View view){
        // Log.d("TestClick", "The ImageButton click works!");
        // 1. The phone number you want to call
        String phoneNumber = "123456789";

        // 2. Create the Intent to open the dialer
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);

        // 3. Give the Intent the phone number (must start with "tel:")
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));

        // 4. Launch the phone app
        startActivity(dialIntent);
    }

    public void btnClickSearch(View view){
        // 1. The URL you want to open
        // NOTE: It MUST start with "http://" or "https://"
        String url = "https://www.google.com";

        // 2. Create the Intent to view the webpage
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);

        // 3. Give the Intent the URL data
        browserIntent.setData(Uri.parse(url));

        // 4. Launch the browser app
        startActivity(browserIntent);
    }
    // Fruit Handler
    public void btnClickFruit(View view){
         showFoodView(btnFruit.getText().toString(),R.drawable.fruits);
    }
    public void btnClickVegetable(View view){
        showFoodView(btnVegetable.getText().toString(),R.drawable.vegetable);
    }
    public void btnClickDrink(View view){
        showFoodView(btnDrink.getText().toString(),R.drawable.drink);
    }

    // Helper Method
    private void showFoodView(String foodName, int drawableImage){
        Bundle dataBundle = new Bundle();
        dataBundle.putString("name",foodName);
        dataBundle.putInt("image",drawableImage);

        // sender and receiver of intent
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), com.sochoeun.myapplication.ItemDetail.class);
        intent.putExtras(dataBundle);

        // transmit intent
        startActivity(intent);
    }
}

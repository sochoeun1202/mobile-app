package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

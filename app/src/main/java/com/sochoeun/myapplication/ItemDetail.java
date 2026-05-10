package com.sochoeun.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        receiverData();
    }

    public  void btnBackHome(View v){
        finish();
    }

    // Helper Method: Receive data
    private void receiverData(){
        // get the data passed from main activity
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String foodName = bundle.getString("name");
        int foodImg =  bundle.getInt("image");

        TextView tvFoodName = (TextView) findViewById(R.id.txItemName);
        ImageView imgFood = (ImageView) findViewById(R.id.imgFood);

        // set value
        tvFoodName.setText(foodName);
        imgFood.setImageResource(foodImg);
    }
}
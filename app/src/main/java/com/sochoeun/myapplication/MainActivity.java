package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void backButtonClickListener(View view){
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etLastName = findViewById(R.id.etLastName);
        EditText etPhone = findViewById(R.id.etPhone);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etHouse = findViewById(R.id.etHouse);
        EditText etStreet = findViewById(R.id.etStreet);
        EditText etSangKat = findViewById(R.id.etSangKat);
        EditText etKhan = findViewById(R.id.etKhan);
        EditText etCity = findViewById(R.id.etCity);

        Person p = new Person();
        p.setFirstname(etFirstName.getText().toString());
        p.setLastname(etLastName.getText().toString());
        p.setPhone(etPhone.getText().toString());
        p.setEmail(etEmail.getText().toString());
        p.setHouse(etHouse.getText().toString());
        p.setStreet(etStreet.getText().toString());
        p.setSangKat(etSangKat.getText().toString());
        p.setKhan(etKhan.getText().toString());
        p.setCity(etCity.getText().toString());

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("person", p);
        startActivity(intent);
    }
}

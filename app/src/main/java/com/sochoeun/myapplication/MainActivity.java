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
        EditText lastnameEdit = findViewById(R.id.etFirstName);
        Bundle bundle = new Bundle();
        //bundle.putString("lastname", lastnameEdit.getText().toString());
        String lastname = lastnameEdit.getText().toString();
        Person p = new Person();
        p.setLastname(lastname);
        bundle.putParcelable("person", p);
        Intent resultIntent = new Intent();
        resultIntent.putExtras(bundle);
        setResult(RESULT_OK, resultIntent);
        finish(); // End SecondActivity and return result

    }
}

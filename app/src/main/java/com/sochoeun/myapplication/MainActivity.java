package com.sochoeun.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Add Menu to toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Use Context Menu on TextView
        // TextView tv = findViewById(R.id.textView);
        // registerForContextMenu(tv);

        // list view
        //Data Source
        String tutorials[] = {
                "Algorithms", "Data Structures", "Languages",
                "Interview Corner", "GATE", "ISRO CS",
                "UGC NET CS", "CS Subjects", "Web Technologies"
        };
        //Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tutorials);
        //ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Handle item click events
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int i, long id) {
                        String selected = (String) listView.getItemAtPosition(i);
                        intent.putExtra("SELECTED_ITEM", selected);
                        startActivity(intent);
//                        Toast.makeText(getApplicationContext(),
//                                selected, Toast.LENGTH_LONG).show();
                    }
                });

        //Register for context menu
        registerForContextMenu(listView);
    }

    // Register Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    // Register Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // Render Condition
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_setting){
            Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_about){
            Toast.makeText(this,"Abouts",Toast.LENGTH_SHORT).show();
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

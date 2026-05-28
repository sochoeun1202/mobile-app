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
        java.util.List<Province> provinces = new java.util.ArrayList<>();
        String cambodiaProvinces[] = {
                "1, Phnom Penh, ភ្នំពេញ, phnom_penh.png",
                "2, Banteay Meanchey, បន្ទាយមានជ័យ, banteay_meanchey.png",
                "3, Battambang, បាត់ដំបង, battambang.png",
                "4, Kampong Cham, កំពង់ចាម, kampong_cham.png",
                "5, Kampong Chhnang, កំពង់ឆ្នាំង, kampong_chhnang.png",
                "6, Kampong Speu, កំពង់ស្ពឺ, kampong_speu.png",
                "7, Kampong Thom, កំពង់ធំ, kampong_thom.png",
                "8, Kampot, កំពត, kampot.png",
                "9, Kandal, កណ្តាល, kandal.png",
                "10, Kep, កែប, kep.png",
                "11, Koh Kong, កោះកុង, koh_kong.png",
                "12, Kratie, ក្រចេះ, kratie.png",
                "13, Mondulkiri, មណ្ឌលគិរី, mondulkiri.png",
                "14, Oddar Meanchey, ឧត្តរមានជ័យ, oddar_meanchey.png",
                "15, Pailin, ប៉ៃលិន, pailin.png",
                "16, Preah Sihanouk, ព្រះសីហនុ, preah_sihanouk.png",
                "17, Preah Vihear, ព្រះវិហារ, preah_vihear.png",
                "18, Prey Veng, ព្រៃវែង, prey_veng.png",
                "19, Pursat, ពោធិ៍សាត់, pursat.png",
                "20, Ratanakiri, រតនគិរី, ratanakiri.png",
                "21, Siem Reap, សៀមរាប, siem_reap.png",
                "22, Stung Treng, ស្ទឹងត្រែង, stung_treng.png",
                "23, Svay Rieng, ស្វាយរៀង, svay_rieng.png",
                "24, Takeo, តាកែវ, takeo.png",
                "25, Tboung Khmum, ត្បូងឃ្មុំ, tboung_khmum.png"
        };

        for (String s : cambodiaProvinces) {
            String[] parts = s.split(", ");
            if (parts.length == 4) {
                int id = Integer.parseInt(parts[0]);
                String nameEn = parts[1];
                String nameKh = parts[2];
                String imageName = parts[3];
                provinces.add(new Province(id, nameEn, nameKh, imageName));
            }
        }

        //Adapter
        ProvinceAdapter adapter = new ProvinceAdapter(this, provinces);
        //ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Handle item click events
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int i, long id) {
                        Province selected = (Province) listView.getItemAtPosition(i);
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("SELECTED_PROVINCE", selected);
                        startActivity(intent);
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

package com.sochoeun.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProvinceAdapter extends ArrayAdapter<Province> {
    private Context context;
    private List<Province> provinces;

    public ProvinceAdapter(@NonNull Context context, @NonNull List<Province> provinces) {
        super(context, R.layout.list_item_province, provinces);
        this.context = context;
        this.provinces = provinces;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_province, parent, false);
        }

        Province province = provinces.get(position);

        ImageView imageView = convertView.findViewById(R.id.provinceImage);
        TextView nameEnView = convertView.findViewById(R.id.provinceNameEn);
        TextView nameKhView = convertView.findViewById(R.id.provinceNameKh);

        nameEnView.setText(province.getNameEn());
        nameKhView.setText(province.getNameKh());

        // Handle image
        String imageName = province.getImageName().replace(".png", "");
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        
        if (resId != 0) {
            imageView.setImageResource(resId);
        } else {
            // Fallback image
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }

        return convertView;
    }
}

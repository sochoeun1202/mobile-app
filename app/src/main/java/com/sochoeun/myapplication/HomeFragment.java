package com.sochoeun.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(String username) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("USERNAME", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        TextView tvUsernameDisplay = view.findViewById(R.id.tv_username_display);
        
        String username = null;
        if (getArguments() != null) {
            username = getArguments().getString("USERNAME");
        }
        if (username == null && getActivity() != null) {
            username = getActivity().getIntent().getStringExtra("USERNAME");
        }
        
        if (username != null && !username.trim().isEmpty()) {
            tvUsernameDisplay.setText(username.toUpperCase());
        } else {
            tvUsernameDisplay.setText("CHAMPION");
        }
        
        return view;
    }
}

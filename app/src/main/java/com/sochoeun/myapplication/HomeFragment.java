package com.sochoeun.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    private String username;

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
        // Read subscription state from SharedPreferences
        SharedPreferences prefs = null;
        if (getActivity() != null) {
            prefs = getActivity().getSharedPreferences("metabolic_prefs", Context.MODE_PRIVATE);
        }
        boolean isSubscribed = prefs != null && prefs.getBoolean("is_subscribed", false);

        View view;
        username = null;
        if (getArguments() != null) {
            username = getArguments().getString("USERNAME");
        }
        if (username == null && getActivity() != null) {
            username = getActivity().getIntent().getStringExtra("USERNAME");
        }
        
        String displayName = (username != null && !username.trim().isEmpty()) ? username.toUpperCase() : "CHAMPION";
        String displayNormalName = (username != null && !username.trim().isEmpty()) ? username : "Alex Rivera";

        if (isSubscribed) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            TextView tvUsernameDisplay = view.findViewById(R.id.tv_username_display);
            if (tvUsernameDisplay != null) {
                tvUsernameDisplay.setText(displayNormalName);
            }
            TextView tvPlanRemaining = view.findViewById(R.id.tv_plan_remaining);
            TextView tvPlanExpiration = view.findViewById(R.id.tv_plan_expiration);
            if (prefs != null) {
                String remaining = prefs.getString("plan_remaining", "7 Days");
                String expiration = prefs.getString("plan_expiration", "June 28, 2026");
                if (tvPlanRemaining != null) {
                    tvPlanRemaining.setText(remaining);
                }
                if (tvPlanExpiration != null) {
                    tvPlanExpiration.setText("Expires: " + expiration);
                }
            }
        } else {
            view = inflater.inflate(R.layout.fragment_home_unsubscribed, container, false);
            TextView tvAthleteName = view.findViewById(R.id.tv_athlete_name);
            if (tvAthleteName != null) {
                tvAthleteName.setText(displayNormalName);
            }
            MaterialCardView cardUnlockPlan = view.findViewById(R.id.card_unlock_plan);
            if (cardUnlockPlan != null) {
                cardUnlockPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).switchFragment(1); // Index 1 is Subscription
                        }
                    }
                });
            }
        }

        return view;
    }
}

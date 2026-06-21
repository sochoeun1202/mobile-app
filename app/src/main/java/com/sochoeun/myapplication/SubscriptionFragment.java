package com.sochoeun.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class SubscriptionFragment extends Fragment {

    private Button btnSubscribe;
    private TextView tvPlanLabel, tvPlanName;
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);

        btnSubscribe = view.findViewById(R.id.btn_subscribe);
        tvPlanLabel = view.findViewById(R.id.tv_plan_label);
        tvPlanName = view.findViewById(R.id.tv_plan_name);

        if (getActivity() != null) {
            prefs = getActivity().getSharedPreferences("metabolic_prefs", Context.MODE_PRIVATE);
        }

        updateUI();

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefs == null) return;

                boolean isSubscribed = prefs.getBoolean("is_subscribed", false);
                SharedPreferences.Editor editor = prefs.edit();

                if (isSubscribed) {
                    // Cancel subscription
                    editor.putBoolean("is_subscribed", false);
                    editor.apply();
                    Toast.makeText(getActivity(), "Subscription Cancelled.", Toast.LENGTH_SHORT).show();
                } else {
                    // Subscribe
                    editor.putBoolean("is_subscribed", true);
                    editor.apply();
                    Toast.makeText(getActivity(), "Successfully Subscribed to Metabolic Pro!", Toast.LENGTH_SHORT).show();
                }

                // Update UI state
                updateUI();

                // Auto navigate back to Home to see the state changes
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchFragment(0);
                }
            }
        });

        return view;
    }

    private void updateUI() {
        if (prefs == null || btnSubscribe == null) return;

        boolean isSubscribed = prefs.getBoolean("is_subscribed", false);

        if (isSubscribed) {
            tvPlanLabel.setText("ACTIVE PLAN");
            tvPlanLabel.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_green));
            tvPlanName.setText("METABOLIC PRO (ACTIVE)");
            btnSubscribe.setText("CANCEL SUBSCRIPTION");
            btnSubscribe.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.divider_color)));
        } else {
            tvPlanLabel.setText("CURRENT PLAN");
            tvPlanLabel.setTextColor(ContextCompat.getColor(requireContext(), R.color.label_grey));
            tvPlanName.setText("METABOLIC PRO");
            btnSubscribe.setText("SUBSCRIBE NOW");
            btnSubscribe.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary_green)));
        }
    }
}

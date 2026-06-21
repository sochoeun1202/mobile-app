package com.sochoeun.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;

public class SubscriptionFragment extends Fragment {

    private MaterialCardView cardBronze, cardElite, cardMaster, cardVip;
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);

        cardBronze = view.findViewById(R.id.card_bronze);
        cardElite = view.findViewById(R.id.card_elite);
        cardMaster = view.findViewById(R.id.card_master);
        cardVip = view.findViewById(R.id.card_vip);

        if (getActivity() != null) {
            prefs = getActivity().getSharedPreferences("metabolic_prefs", Context.MODE_PRIVATE);
        }

        updateBorders();

        cardBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTierSelection("Bronze", "1 Day", "June 22, 2026");
            }
        });

        cardElite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTierSelection("Elite", "7 Days", "June 28, 2026");
            }
        });

        cardMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTierSelection("Master", "30 Days", "July 21, 2026");
            }
        });

        cardVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTierSelection("VIP", "365 Days", "June 21, 2027");
            }
        });

        return view;
    }

    private void handleTierSelection(final String tier, final String remaining, final String expiration) {
        if (prefs == null) return;

        boolean isCurrentlySubscribed = prefs.getBoolean("is_subscribed", false);
        String currentTier = prefs.getString("subscribed_tier", "");

        if (isCurrentlySubscribed && currentTier.equals(tier)) {
            // Cancel current subscription confirmation dialog
            new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Cancel Plan")
                .setMessage("Are you sure you want to cancel your active " + tier + " membership?")
                .setPositiveButton("Yes, Cancel", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("is_subscribed", false);
                        editor.putString("subscribed_tier", "");
                        editor.putString("plan_remaining", "");
                        editor.putString("plan_expiration", "");
                        editor.apply();
                        Toast.makeText(getActivity(), tier + " Plan Cancelled.", Toast.LENGTH_SHORT).show();
                        updateBorders();
                        // Redirect back to HomeFragment to see updated state
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).switchFragment(0);
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
        } else {
            // Subscribe confirmation dialog
            new com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Confirm Subscription")
                .setMessage("Do you want to subscribe to the " + tier + " plan?")
                .setPositiveButton("Confirm", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("is_subscribed", true);
                        editor.putString("subscribed_tier", tier);
                        editor.putString("plan_remaining", remaining);
                        editor.putString("plan_expiration", expiration);
                        editor.apply();
                        Toast.makeText(getActivity(), "Subscribed to " + tier + " Tier successfully!", Toast.LENGTH_SHORT).show();
                        updateBorders();
                        // Redirect back to HomeFragment to see updated plan duration
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).switchFragment(0);
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
        }
    }

    private void updateBorders() {
        if (prefs == null) return;

        boolean isSubscribed = prefs.getBoolean("is_subscribed", false);
        String activeTier = prefs.getString("subscribed_tier", "");

        int activeColor = ContextCompat.getColor(requireContext(), R.color.primary_green);
        int inactiveColor = ContextCompat.getColor(requireContext(), R.color.divider_color);

        // Reset all card strokes
        cardBronze.setStrokeColor(inactiveColor);
        cardElite.setStrokeColor(inactiveColor);
        cardMaster.setStrokeColor(inactiveColor);
        cardVip.setStrokeColor(inactiveColor);

        // Highlight active tier if subscribed
        if (isSubscribed) {
            switch (activeTier) {
                case "Bronze":
                    cardBronze.setStrokeColor(activeColor);
                    break;
                case "Elite":
                    cardElite.setStrokeColor(activeColor);
                    break;
                case "Master":
                    cardMaster.setStrokeColor(activeColor);
                    break;
                case "VIP":
                    cardVip.setStrokeColor(activeColor);
                    break;
            }
        } else {
            // Default mockup state: highlight Elite as popular choice when unsubscribed
            cardElite.setStrokeColor(activeColor);
        }
    }
}

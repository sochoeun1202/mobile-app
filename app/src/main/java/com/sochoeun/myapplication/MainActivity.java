package com.sochoeun.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private LinearLayout tabHome, tabSubscription, tabCoach, tabProfile;
    private ImageView ivTabHome, ivTabSubscription, ivTabCoach, ivTabProfile;
    private TextView tvTabHome, tvTabSubscription, tvTabCoach, tvTabProfile;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve username passed from LoginActivity
        username = getIntent().getStringExtra("USERNAME");

        // Initialize views
        tabHome = findViewById(R.id.tab_home);
        tabSubscription = findViewById(R.id.tab_subscription);
        tabCoach = findViewById(R.id.tab_coach);
        tabProfile = findViewById(R.id.tab_profile);

        ivTabHome = findViewById(R.id.iv_tab_home);
        ivTabSubscription = findViewById(R.id.iv_tab_subscription);
        ivTabCoach = findViewById(R.id.iv_tab_coach);
        ivTabProfile = findViewById(R.id.iv_tab_profile);

        tvTabHome = findViewById(R.id.tv_tab_home);
        tvTabSubscription = findViewById(R.id.tv_tab_subscription);
        tvTabCoach = findViewById(R.id.tv_tab_coach);
        tvTabProfile = findViewById(R.id.tv_tab_profile);

        // Setup click listeners
        tabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(0);
            }
        });

        tabSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(1);
            }
        });

        tabCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(2);
            }
        });

        tabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(3);
            }
        });

        // Set default fragment (Home) on launch
        if (savedInstanceState == null) {
            switchFragment(0);
        }
    }

    public void switchFragment(int index) {
        Fragment selectedFragment;
        switch (index) {
            case 0:
                selectedFragment = HomeFragment.newInstance(username);
                break;
            case 1:
                selectedFragment = new SubscriptionFragment();
                break;
            case 2:
                selectedFragment = new CoachFragment();
                break;
            case 3:
                selectedFragment = new ProfileFragment();
                break;
            default:
                selectedFragment = HomeFragment.newInstance(username);
                break;
        }

        // Replace fragment in container
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, selectedFragment);
        transaction.commit();

        // Update Bottom Menu UI representation
        updateBottomNavigationUI(index);
    }

    private void updateBottomNavigationUI(int selectedIndex) {
        int activeColor = ContextCompat.getColor(this, R.color.neutral_white);
        int inactiveColor = ContextCompat.getColor(this, R.color.label_grey);

        // 1. Reset all tabs
        // Home
        tabHome.setBackground(null);
        ivTabHome.setColorFilter(inactiveColor);
        tvTabHome.setTextColor(inactiveColor);

        // Subscription
        tabSubscription.setBackground(null);
        ivTabSubscription.setColorFilter(inactiveColor);
        tvTabSubscription.setTextColor(inactiveColor);

        // Coach
        tabCoach.setBackground(null);
        ivTabCoach.setColorFilter(inactiveColor);
        tvTabCoach.setTextColor(inactiveColor);

        // Profile
        tabProfile.setBackground(null);
        ivTabProfile.setColorFilter(inactiveColor);
        tvTabProfile.setTextColor(inactiveColor);

        // 2. Apply active styling to the selected tab
        switch (selectedIndex) {
            case 0:
                tabHome.setBackgroundResource(R.drawable.tab_selected_background);
                ivTabHome.setColorFilter(activeColor);
                tvTabHome.setTextColor(activeColor);
                break;
            case 1:
                tabSubscription.setBackgroundResource(R.drawable.tab_selected_background);
                ivTabSubscription.setColorFilter(activeColor);
                tvTabSubscription.setTextColor(activeColor);
                break;
            case 2:
                tabCoach.setBackgroundResource(R.drawable.tab_selected_background);
                ivTabCoach.setColorFilter(activeColor);
                tvTabCoach.setTextColor(activeColor);
                break;
            case 3:
                tabProfile.setBackgroundResource(R.drawable.tab_selected_background);
                ivTabProfile.setColorFilter(activeColor);
                tvTabProfile.setTextColor(activeColor);
                break;
        }
    }
}

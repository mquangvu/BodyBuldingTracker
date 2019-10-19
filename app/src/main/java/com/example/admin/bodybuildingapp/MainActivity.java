package com.example.admin.bodybuildingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_workout:
                    fragment = new WorkoutFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_nutrition:
                    fragment = new NutritionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_local_gym:
                    fragment = new MapFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.navigation_tools:
                    fragment = new ToolsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        TextView txtHeight1 = findViewById(R.id.txtHeight1);
        TextView txtHeight2 = findViewById(R.id.txtHeight2);
        TextView txtWeight = findViewById(R.id.txtWeight);

        switch (view.getId()) {
            case R.id.meter_rbtn:
                if (checked)
                    txtHeight1.setHint(getString(R.string.meter));
                txtHeight2.setHint(getString(R.string.centimeter));
                break;
            case R.id.feet_rbtn:
                if (checked)
                    txtHeight1.setHint(getString(R.string.feet));
                txtHeight2.setHint(getString(R.string.inch));
                break;
            case R.id.pound_rbtn:
                if (checked)
                    txtWeight.setHint(getString(R.string.pounds));
                break;
            case R.id.kilo_rbtn:
                if (checked)
                    txtWeight.setHint(getString(R.string.kilograms));
                break;
        }
    }
}














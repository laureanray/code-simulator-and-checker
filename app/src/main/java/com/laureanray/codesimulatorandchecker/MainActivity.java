package com.laureanray.codesimulatorandchecker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d(getLocalClassName(),"onCreate");

        if(SharedPreferencesManager.getIsLoggedInValue(getApplicationContext())) {
            setContentView(R.layout.activity_main);

            // Navigation
            BottomNavigationView navView = findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupWithNavController(navView, navController);
        } else {
            // Launch the login activity
            Intent intent = new Intent(this, RootActivity.class);
            startActivity(intent);
            finish();
        }
    }


}

package com.laureanray.codesimulatorandchecker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laureanray.codesimulatorandchecker.app.SharedPreferencesManager;
import com.laureanray.codesimulatorandchecker.data.model.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity  {
    private static Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get student object from sharedpref
        Log.d(getLocalClassName(),"onCreate");

        if(SharedPreferencesManager.getIsLoggedInValue(getApplicationContext())
           ) {
            // Refresh user information here.
            student = SharedPreferencesManager.getStudentValue(getApplicationContext());

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

    public static Student getStudent() {
        return student;
    }
}

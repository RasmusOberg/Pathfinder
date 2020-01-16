package com.example.pathfinder.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pathfinder.Fragments.LoginFragment;
import com.example.pathfinder.R;
import com.example.pathfinder.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow();
        }
    }
}

package com.example.pathfinder.Main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pathfinder.Fragments.CompassFragment;
import com.example.pathfinder.R;

public class DisplayInformationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_information);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompassFragment()).commit();
        }
    }

}

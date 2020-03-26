package com.example.pathfinder.Main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.example.pathfinder.Fragments.CompassFragment;
import com.example.pathfinder.Fragments.ShowStepsFragment;
import com.example.pathfinder.Fragments.TestFragment;
import com.example.pathfinder.R;
import com.example.pathfinder.ui.main.MainViewModel;
import com.google.android.material.navigation.NavigationView;

public class DisplayInformationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private MyServiceConnection connection;
    private Intent stepsIntent;
    boolean bound = false;
    public StepService service;
    private MainViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_information_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        connection = new MyServiceConnection(this);
        stepsIntent = new Intent(this, StepService.class);
        bindService(stepsIntent, connection, Context.BIND_AUTO_CREATE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompassFragment()).commit();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_show_compass:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompassFragment()).commit();
                break;
            case R.id.nav_show_steps:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShowStepsFragment()).commit();
                break;
            case R.id.nav_overview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestFragment()).commit();
                break;
        }
        menuItem.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createStepsEntry(){
        viewModel.insertStep(new Step(0, DateFormat.getDateFormat(System.currentTimeMillis()), viewModel.getCurrentUser()));
    }

    protected void onDestroy(){
        super.onDestroy();
        if(bound){
            unbindService(connection);
            bound = false;
        }
    }

}

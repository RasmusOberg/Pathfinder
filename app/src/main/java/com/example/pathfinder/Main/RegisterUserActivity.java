package com.example.pathfinder.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.pathfinder.Fragments.LoginViewModel;
import com.example.pathfinder.Fragments.MyViewModelFactory;
import com.example.pathfinder.R;

public class RegisterUserActivity extends AppCompatActivity {
    private static final String TAG = "RegisterUserActivity";
    private EditText etUsername, etPassword;
    private Button register;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate: " );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mViewModel = ViewModelProviders.of(this, new MyViewModelFactory(getApplication())).get(LoginViewModel.class);
        etUsername = findViewById(R.id.newUsername);
        etPassword = findViewById(R.id.newPassword);
        register = findViewById(R.id.buttonRegister);
        initializeComponents();
    }

    private void initializeComponents(){
        Log.d(TAG, "initializeComponents: ");

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(mViewModel.validateUsername(username)){
                    mViewModel.insertNewUser(new User(username, password));
                    Toast.makeText(getApplicationContext(), "Username is free", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Username is taken, please choose another.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

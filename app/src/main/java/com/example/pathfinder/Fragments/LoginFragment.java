package com.example.pathfinder.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pathfinder.Main.DisplayInformationActivity;
import com.example.pathfinder.Main.RegisterUserActivity;
import com.example.pathfinder.R;

public class LoginFragment extends Fragment {
    private LoginViewModel mViewModel;
    private Button loginButton, registerButton;
    private EditText inputUsername, inputPassword;
    private String username, password;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
//        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {
        loginButton = view.findViewById(R.id.buttonLogin);
        registerButton = view.findViewById(R.id.buttonRegister);
        inputUsername = view.findViewById(R.id.inputUsername);
        inputPassword = view.findViewById(R.id.inputPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = inputUsername.getText().toString();
                password = inputPassword.getText().toString();
                if(validateLogin(username, password)){
                    Toast.makeText(getContext(), "Login succesful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), DisplayInformationActivity.class));
                }else{
                    Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterUserActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public boolean validateLogin(String username, String password){
        boolean loginAttempt = mViewModel.validateLogin(username, password);
        return loginAttempt;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mViewModel = ViewModelProviders.of(this, new MyViewModelFactory(getActivity().getApplication())).get(LoginViewModel.class);
        Toast.makeText(getContext(), "Viewmodel created!", Toast.LENGTH_SHORT).show();
        // TODO: Use the ViewModel
    }

}

package com.mycompany.starykitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mycompany.starykitapp.databinding.ActivityMainBinding;
import com.mycompany.starykitapp.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJump.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        });
    }


}
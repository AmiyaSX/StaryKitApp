package com.mycompany.starykitapp.home.ui.game;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.starykitapp.WebViewActivity;
import com.mycompany.starykitapp.config.GameLink;
import com.mycompany.starykitapp.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GameViewModel gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initGameEntrance();

    }

    private void initGameEntrance() {
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        binding.gameCard1.setOnClickListener( v -> {
            intent.putExtra("url", GameLink.WUZIQI_URL);
            startActivity(intent);
        });
        binding.gameCard2.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.WUZIQI_URL);
            startActivity(intent);
        });
        binding.gameCard3.setOnClickListener(v -> {
            //TODO
        });
        binding.gameCard4.setOnClickListener(v -> {
            //TODO
        });
        binding.gameCard5.setOnClickListener(v -> {
            //TODO
        });
        binding.gameCard6.setOnClickListener(v -> {
            //TODO
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.mycompany.starykitapp.home.ui.game;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycompany.starykitapp.R;
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
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //定义回调
        OnBackPressedCallback callback = new OnBackPressedCallback(
                true // default to enabled
        ) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(requireActivity(), "aaaaaa", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home).navigate(R.id.action_back_to_HomeFragment);
            }
        };
        //获取Activity的返回键分发器添加回调
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, // LifecycleOwner
                callback);
    }
    private void initGameEntrance() {
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        binding.gameCard1.setOnClickListener( v -> {
            intent.putExtra("url", GameLink.WUZIQI_URL);
            startActivity(intent);
        });
        binding.gameCard2.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.CHENYUJIELONG_URL);
            startActivity(intent);
        });
        binding.gameCard3.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.LIANLIANKAN_URL);
            startActivity(intent);
        });
        binding.gameCard4.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.TIANSE_URL);
            startActivity(intent);
        });
        binding.gameCard5.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.SHU_DU_URL);
            startActivity(intent);
        });
        binding.gameCard6.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.XIAOXIAOLE_URL);
            startActivity(intent);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
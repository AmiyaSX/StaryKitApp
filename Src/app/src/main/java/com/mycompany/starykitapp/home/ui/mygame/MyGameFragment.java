package com.mycompany.starykitapp.home.ui.mygame;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.WebViewActivity;
import com.mycompany.starykitapp.config.GameLink;
import com.mycompany.starykitapp.databinding.FragmentGameBinding;
import com.mycompany.starykitapp.databinding.FragmentMyGameBinding;

public class MyGameFragment extends Fragment {

    private MyGameViewModel mViewModel;

    public static MyGameFragment newInstance() {
        return new MyGameFragment();
    }

    private FragmentMyGameBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMyGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initEvent();
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
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home).navigate(R.id.action_back_to_MeFragment);
            }
        };
        //获取Activity的返回键分发器添加回调
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, // LifecycleOwner
                callback);
    }

    private void initEvent() {
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        binding.wuziqi.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.WUZIQI_URL);
            startActivity(intent);
        });
        binding.shudu.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.SHU_DU_URL);
            startActivity(intent);
        });
        binding.llk.setOnClickListener(v -> {
            intent.putExtra("url", GameLink.LIANLIANKAN_URL);
            startActivity(intent);
        });
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home);
        binding.ball.setOnClickListener(v -> {
            navController.navigate(R.id.action_back_to_MeFragment);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyGameViewModel.class);
        // TODO: Use the ViewModel
    }

}
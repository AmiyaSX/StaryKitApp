package com.mycompany.starykitapp.home.ui.mystory;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.WebViewActivity;
import com.mycompany.starykitapp.config.StoryLink;
import com.mycompany.starykitapp.databinding.FragmentMyStoryBinding;
import com.mycompany.starykitapp.home.ui.mystory.MyStoryViewModel;


public class MyStoryFragment extends Fragment {

    private MyStoryViewModel mViewModel;
    private FragmentMyStoryBinding binding;

    public static MyStoryFragment newInstance() {
        return new MyStoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMyStoryBinding.inflate(inflater, container, false);
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
        binding.mystoryCard1.setOnClickListener(v ->{
            intent.putExtra("url", StoryLink.STORY_YIQIANLINGYI_URL);
            startActivity(intent);
        });
        binding.mystoryCard2.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_BAOHULU_URL);
            startActivity(intent);
        });
        binding.mystudyCard3.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_DAOCAOREN_URL);
            startActivity(intent);
        });
        binding.mystoryCard4.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_XIAOWANGZI_URL);
            startActivity(intent);
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyStoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
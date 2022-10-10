package com.mycompany.starykitapp.home.ui.story;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.WebViewActivity;
import com.mycompany.starykitapp.config.StoryLink;
import com.mycompany.starykitapp.databinding.FragmentStoryBinding;

public class StoryFragment extends Fragment {

    private StoryViewModel mViewModel;
    private FragmentStoryBinding binding;
    public static StoryFragment newInstance() {
        return new StoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStoryBinding.inflate(inflater, container, false);
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        binding.aaa.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_ALICE_URL);
            startActivity(intent);
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
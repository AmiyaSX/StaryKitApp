package com.mycompany.starykitapp.home.ui.study;

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
import com.mycompany.starykitapp.config.StudyLink;
import com.mycompany.starykitapp.databinding.FragmentStudyBinding;

public class StudyFragment extends Fragment {

    private StudyViewModel mViewModel;
    private FragmentStudyBinding binding;
    public static StudyFragment newInstance() {
        return new StudyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStudyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        binding.studyCard1.setOnClickListener(v -> {
            intent.putExtra("url", StudyLink.MATH_EASY_URL);
            startActivity(intent);
        });
        binding.studyCard2.setOnClickListener(v -> {
            intent.putExtra("url", StudyLink.MATH_MIDIUM_URL);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StudyViewModel.class);
        // TODO: Use the ViewModel
    }

}
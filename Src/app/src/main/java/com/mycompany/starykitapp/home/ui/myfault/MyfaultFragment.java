package com.mycompany.starykitapp.home.ui.myfault;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.databinding.FragmentGameBinding;
import com.mycompany.starykitapp.databinding.FragmentMyfaultBinding;
import com.mycompany.starykitapp.home.ui.game.GameViewModel;

public class MyfaultFragment extends Fragment {

    private FragmentMyfaultBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyfaultViewModel myfaultViewModel =
                new ViewModelProvider(this).get(MyfaultViewModel.class);

        binding = FragmentMyfaultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

package com.mycompany.starykitapp.home.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    private @NonNull FragmentMeBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeViewModel meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);

        binding = FragmentMeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home);
        initEvent();
    }

    private void initEvent() {
        binding.mineStory.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigate_to_MystoryFragment);
        });
        binding.mineFunction.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigate_to_MyfaultFragment);
        });
        binding.mineGame.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigate_to_MygameFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
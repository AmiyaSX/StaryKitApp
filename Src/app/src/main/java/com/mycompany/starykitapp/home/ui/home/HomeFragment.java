package com.mycompany.starykitapp.home.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home);
        binding.gameCard.setOnClickListener( v -> {
            navController.navigate(R.id.action_navigate_to_GameFragment);
        });
        binding.studyCard.setOnClickListener( v -> {
            navController.navigate(R.id.action_navigate_to_StudyFragment);
        });
        binding.storyCard.setOnClickListener( v -> {
            navController.navigate(R.id.action_navigate_to_StoryFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
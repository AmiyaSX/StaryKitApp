package com.mycompany.starykitapp.home.ui.story;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.WebViewActivity;
import com.mycompany.starykitapp.config.StoryLink;
import com.mycompany.starykitapp.databinding.FragmentStoryBinding;
import com.mycompany.starykitapp.home.DataBean;
import com.mycompany.starykitapp.utils.ImageAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment {

    private StoryViewModel mViewModel;
    private FragmentStoryBinding binding;
    private List<DataBean> dataBeans = new ArrayList<>();
    public static StoryFragment newInstance() {
        return new StoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStoryBinding.inflate(inflater, container, false);
        dataBeans.add(new DataBean(R.drawable.img1));
        dataBeans.add(new DataBean(R.drawable.img2));
        dataBeans.add(new DataBean(R.drawable.img3));
        dataBeans.add(new DataBean(R.drawable.img4));
        initEvent();
        return binding.getRoot();
    }
    public void useBanner() {
        binding.banner.addBannerLifecycleObserver(requireActivity())//添加生命周期观察者
                .setAdapter(new ImageAdapter(dataBeans))
                .addBannerLifecycleObserver(requireActivity())//添加生命周期观察者
                .setIndicator(new CircleIndicator(requireActivity()));
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
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_home).navigate(R.id.action_back_to_HomeFragment);
            }
        };
        //获取Activity的返回键分发器添加回调
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, // LifecycleOwner
                callback);
    }
    private void initEvent() {
        Intent intent = new Intent(requireActivity(), WebViewActivity.class);
        useBanner();
        binding.storyCard1.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_ALICE_URL);
            startActivity(intent);
        });
        binding.storyCard2.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_XIAOWANGZI_URL);
            startActivity(intent);
        });
        binding.storyCard3.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_BAOHULU_URL);
            startActivity(intent);
        });
        binding.storyCard4.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_JRGWSTGM_URL);
            startActivity(intent);
        });
        binding.storyCard5.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_YIQIANLINGYI_URL);
            startActivity(intent);
        });
        binding.storyCard6.setOnClickListener(v -> {
            intent.putExtra("url", StoryLink.STORY_DAOCAOREN_URL);
            startActivity(intent);
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
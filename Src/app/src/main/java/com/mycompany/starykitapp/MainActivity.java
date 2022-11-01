package com.mycompany.starykitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mycompany.starykitapp.databinding.ActivityMainBinding;
import com.mycompany.starykitapp.home.HomeActivity;
import com.mycompany.starykitapp.login.view.LoginActivity;
import com.mycompany.starykitapp.utils.CustomAdapt;

public class MainActivity extends AppCompatActivity implements CustomAdapt {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJump.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        });
    }
    private void configUnits() {
        //AndroidAutoSize 默认开启对 dp 的支持, 调用 UnitsManager.setSupportDP(false); 可以关闭对 dp 的支持
        //主单位 dp 和 副单位可以同时开启的原因是, 对于旧项目中已经使用了 dp 进行布局的页面的兼容
        //让开发者的旧项目可以渐进式的从 dp 切换到副单位, 即新页面用副单位进行布局, 然后抽时间逐渐的将旧页面的布局单位从 dp 改为副单位
        //最后将
    }

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }
}
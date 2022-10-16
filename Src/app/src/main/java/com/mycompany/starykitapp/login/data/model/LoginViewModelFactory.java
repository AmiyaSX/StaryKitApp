package com.mycompany.starykitapp.login.data.model;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.mycompany.starykitapp.login.data.LoginDataSource;
import com.mycompany.starykitapp.login.data.LoginRepository;
import com.mycompany.starykitapp.login.data.model.LoginViewModel;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private SharedPreferences sharedPreferences;
    public LoginViewModelFactory(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(sharedPreferences)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
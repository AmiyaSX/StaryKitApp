package com.mycompany.starykitapp.login.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.login.data.LoginFormState;
import com.mycompany.starykitapp.login.data.LoginRepository;
import com.mycompany.starykitapp.login.data.LoginResult;
import com.mycompany.starykitapp.login.data.RegisterFormState;
import com.mycompany.starykitapp.login.data.RegisterResult;
import com.mycompany.starykitapp.login.data.Result;
import com.mycompany.starykitapp.login.data.LoggedInUser;
import com.mycompany.starykitapp.login.view.LoggedInUserView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void login(String phoneNumber, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(phoneNumber, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void register(String phoneNumber, String password, String password2) {
        Result<LoggedInUser> result = loginRepository.register(phoneNumber, password, password2);
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            registerResult.setValue(new RegisterResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.register_failed));
        }
    }

    public void loginDataChanged(String phoneNumber, String password) {
        if (!isPhoneNumberValid(phoneNumber)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phoneNumber, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    public void registerDataChanged(String phoneNumber, String password1, String password2) {
        if (!isPhoneNumberValid(phoneNumber)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_phoneNumber, null, null));
        } else if (!isPasswordValid(password1)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null));
        } else if (!isPasswordValid(password1, password2)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.mismatch_password));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder phoneNumber validation check
    private boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        return pattern.matcher(phoneNumber).matches();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isPasswordValid(String password1, String password2) {
        return password1.equals(password2);
    }

}
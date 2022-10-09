package com.mycompany.starykitapp.login.view;

import android.app.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.databinding.ActivityLoginBinding;
import com.mycompany.starykitapp.login.data.LoginFormState;
import com.mycompany.starykitapp.login.data.LoginResult;
import com.mycompany.starykitapp.login.data.model.LoginViewModel;
import com.mycompany.starykitapp.login.data.model.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewEventInit();
    }

    private void viewEventInit() {
        final EditText phoneNumberEditText = binding.phoneNumberEt;
        final EditText passwordEditText = binding.passwordEt;
        final TextView loginButton = binding.loginTv;
        final ProgressBar loadingProgressBar = binding.loading;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            if (loginFormState.isDataValid()) {
                loginButton.setBackground(getDrawable(R.drawable.bg_login_btn_able));
            } else {
                loginButton.setBackground(getDrawable(R.drawable.bg_login_btn_unable));
            }
            if (loginFormState.getPhoneNumberError() != null) {
                phoneNumberEditText.setError(getString(loginFormState.getPhoneNumberError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);
            //Complete and destroy login activity once successful
            finish();
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(phoneNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        phoneNumberEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(phoneNumberEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        binding.phoneDeleteIc.setOnClickListener(v -> {
            phoneNumberEditText.setText("");
        });

        binding.passwordEyeIc.setOnClickListener(v -> {
            if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.passwordEyeIc.setImageDrawable(getDrawable(R.drawable.ic_preview_close));
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                binding.passwordEyeIc.setImageDrawable(getDrawable(R.drawable.ic_preview_open));
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });

        binding.tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(phoneNumberEditText.getText().toString(),
                    passwordEditText.getText().toString());
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome);
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
package com.mycompany.starykitapp.login.view;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.starykitapp.R;
import com.mycompany.starykitapp.databinding.ActivityRegisterBinding;
import com.mycompany.starykitapp.home.HomeActivity;
import com.mycompany.starykitapp.login.data.RegisterFormState;
import com.mycompany.starykitapp.login.data.model.LoginViewModel;
import com.mycompany.starykitapp.login.data.model.LoginViewModelFactory;

public class RegisterActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityRegisterBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory(sharedPreferences))
                .get(LoginViewModel.class);
    }
    @Override
    protected void onResume() {
        super.onResume();
        viewEventInit();
    }

    private void viewEventInit() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        final EditText phoneNumberEditText = binding.registerPhoneNumberEt;
        final EditText passwordEditText1 = binding.registerFirstPasswordEt;
        final EditText passwordEditText2 = binding.registerSecondPasswordEt;
        final TextView registerButton = binding.registerTv;
        final ProgressBar loadingProgressBar = binding.loading;
        loginViewModel.getRegisterFormState().observe(this, registerFormState -> {
            if (registerFormState == null) {
                return;
            }
            if (registerFormState.isDataValid()) {
                registerButton.setClickable(true);
                registerButton.setOnClickListener(v -> {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    if (v.isClickable()) {
                        loginViewModel.register(phoneNumberEditText.getText().toString(),
                                passwordEditText1.getText().toString(),passwordEditText2.getText().toString());
                    }
                });
                registerButton.setBackground(getDrawable(R.drawable.bg_register_btn_able));
            } else {
                registerButton.setClickable(false);
                registerButton.setBackground(getDrawable(R.drawable.bg_register_btn_unable));
            }
            if (registerFormState.getPhoneNumberError() != null) {
                phoneNumberEditText.setError(getString(registerFormState.getPhoneNumberError()));
            }
            if (registerFormState.getPasswordFirstError() != null) {
                passwordEditText1.setError(getString(registerFormState.getPasswordFirstError()));
            }
            if (registerFormState.getPasswordSecondError() != null) {
                passwordEditText2.setError(getString(registerFormState.getPasswordSecondError()));
            }
        });
        loginViewModel.getRegisterResult().observe(this, registerResult -> {
            if (registerResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (registerResult.getError() != null) {
                showLoginFailed(registerResult.getError());
            }
            if (registerResult.getSuccess() != null) {
                sharedPreferences.edit().putString(phoneNumberEditText.getText().toString(),passwordEditText1.getText().toString()).commit();
                updateUiWithUser(registerResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);
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
                loginViewModel.registerDataChanged(phoneNumberEditText.getText().toString(),
                        passwordEditText1.getText().toString(),
                        passwordEditText2.getText().toString());
            }
        };
        phoneNumberEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText1.addTextChangedListener(afterTextChangedListener);
        passwordEditText2.addTextChangedListener(afterTextChangedListener);
        passwordEditText1.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.register(phoneNumberEditText.getText().toString(),
                        passwordEditText1.getText().toString(), passwordEditText2.getText().toString());
            }
            return false;
        });
        binding.phoneDeleteIc.setOnClickListener(v -> {
            phoneNumberEditText.setText("");
        });

        binding.passwordEyeIc1.setOnClickListener(v -> {
            if (passwordEditText1.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.passwordEyeIc1.setImageDrawable(getDrawable(R.drawable.ic_preview_close));
                passwordEditText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                binding.passwordEyeIc1.setImageDrawable(getDrawable(R.drawable.ic_preview_open));
                passwordEditText1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
        binding.passwordEyeIc2.setOnClickListener(v -> {
            if (passwordEditText2.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.passwordEyeIc2.setImageDrawable(getDrawable(R.drawable.ic_preview_close));
                passwordEditText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                binding.passwordEyeIc2.setImageDrawable(getDrawable(R.drawable.ic_preview_open));
                passwordEditText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome);
        Intent intent = new Intent(this, HomeActivity.class);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
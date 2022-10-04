package com.mycompany.starykitapp.login.data;

import androidx.annotation.Nullable;

import com.mycompany.starykitapp.login.view.LoggedInUserView;

public class RegisterResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    public RegisterResult(@Nullable Integer error) {
        this.error = error;
    }

    public RegisterResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    public LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}

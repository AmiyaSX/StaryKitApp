package com.mycompany.starykitapp.login.data;

import androidx.annotation.Nullable;

public class RegisterFormState {
    @Nullable
    private Integer phoneNumberError;
    @Nullable
    private Integer passwordFirstError;
    @Nullable
    private Integer passwordSecondError;

    private boolean isDataValid;

    public RegisterFormState(@Nullable Integer phoneNumberError,@Nullable  Integer passwordFirstError,@Nullable  Integer passwordSecondError) {
        this.phoneNumberError = phoneNumberError;
        this.passwordFirstError = passwordFirstError;
        this.passwordSecondError = passwordSecondError;
        this.isDataValid = false;
    }

    public RegisterFormState(boolean isDataValid) {
        this.phoneNumberError = null;
        this.passwordFirstError = null;
        this.passwordSecondError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getPhoneNumberError() {
        return phoneNumberError;
    }

    @Nullable
    public Integer getPasswordFirstError() {
        return passwordFirstError;
    }
    @Nullable
    public Integer getPasswordSecondError() {
        return passwordSecondError;
    }


    public boolean isDataValid() {
        return isDataValid;
    }

}

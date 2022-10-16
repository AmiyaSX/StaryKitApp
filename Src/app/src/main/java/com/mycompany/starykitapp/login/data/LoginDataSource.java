package com.mycompany.starykitapp.login.data;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.Objects;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private SharedPreferences sharedPreferences;

    public LoginDataSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Result<LoggedInUser> login(String username, String password) {
        if (sharedPreferences.getString(username, "").equals("")) {
            return new Result.Error(new IOException("Error logging in"));
        } else {
            LoggedInUser user =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(user);
        }
    }

    public Result<LoggedInUser> register(String username, String password, String password2) {
        Log.d("aaa", "register: " + sharedPreferences.getString(username, "").toString());
        if (sharedPreferences.getString(username, "").equals("") == false) {
            return new Result.Error(new IOException("Error logging in"));
        } else {
            LoggedInUser user =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(user);
        }
    }

    public void logout() {

    }
}
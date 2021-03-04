package com.sip.gestibank.remote;

import com.sip.gestibank.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthService {

    @GET("users/{email}")
    Call<User> getUserByEmail(@Path("email") String email);
}

package com.example.marton.stephane.mobsoft_lab.network.User;


import com.example.marton.stephane.mobsoft_lab.models.Profile;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserApi {
  
  /**
   * Login user
   * Send user name and password, and the system will try to sign it.
   * @param username User name.
   * @param password User password.
   * @return Call<Profile>
   */
  
  @FormUrlEncoded
  @POST("login")
  Call<Profile> loginPost(
    @Field("username") String username, @Field("password") String password
  );

  
}

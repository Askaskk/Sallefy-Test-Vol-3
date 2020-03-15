package com.salle.android.sallefy_test_vol_3.controller.restapi.service;

import com.salle.android.sallefy_test_vol_3.model.UserLogin;
import com.salle.android.sallefy_test_vol_3.model.UserToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserTokenService {

    @POST("authenticate")
    Call<UserToken> loginUser(@Body UserLogin login);


}

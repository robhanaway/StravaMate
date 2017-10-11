package com.rh.stravamate.model.datalayer.network;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public interface AuthService {
    @POST("oauth/token")
    Call<AuthResponse> auth(@Query("client_id") String clientId,
                            @Query("client_secret")String clientSecret,
                            @Query("code") String code);
}

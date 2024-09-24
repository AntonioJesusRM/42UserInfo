package com.example.a42userinfo.data.repository.remote.backend

import com.example.a42userinfo.data.repository.remote.response.GetDataResponse
import com.example.a42userinfo.data.repository.remote.response.PostTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //Token
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun postToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String
    ): Response<PostTokenResponse>

    //User Data
    @GET("v2/me")
    suspend fun getData(): Response<GetDataResponse>
}
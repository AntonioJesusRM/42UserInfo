package com.example.a42userinfo.hilt

import com.example.a42userinfo.data.repository.remote.backend.ApiService
import com.example.a42userinfo.data.repository.remote.backend.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleHilt {
    @Provides
    @Singleton
    fun provideApiService42(retrofitClient: RetrofitClient): ApiService {
        return retrofitClient.retrofit.create(ApiService::class.java)
    }
}
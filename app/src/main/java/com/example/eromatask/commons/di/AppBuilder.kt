package com.example.eromatask.commons.di

import com.example.eromatask.commons.networks.PostsApi
import com.example.eromatask.commons.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppBuilder {


    @Provides
    @Singleton
    fun retrofitBuilder() = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun postsProvider(retrofit: Retrofit) = retrofit.create(PostsApi::class.java)
}
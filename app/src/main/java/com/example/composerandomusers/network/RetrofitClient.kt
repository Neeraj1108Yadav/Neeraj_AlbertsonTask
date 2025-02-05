package com.example.composerandomusers.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://randomuser.me/"

    val apiClient = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)


    private fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
package com.example.tasker.data

import com.example.tasker.data.repository.ApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.tasker.BuildConfig

object RetrofitInstance {
    private const val BASE_URL = BuildConfig.API_BASE_URL

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val ApiClient: ApiRepository by lazy {
        retrofit.create(ApiRepository::class.java)
    }
}
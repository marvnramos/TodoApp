package com.example.tasker.data.repository

import com.example.tasker.data.models.Get
import retrofit2.http.GET
import retrofit2.Call

interface ApiRepository {
//    @GET("/v1/tasks")
//    suspend fun getTasks(
//        @Header("Authorization") authorization: String
//    ): List<Tasks>

    @GET("/v1/tasks")
    fun getHello(): Call<Get>
}
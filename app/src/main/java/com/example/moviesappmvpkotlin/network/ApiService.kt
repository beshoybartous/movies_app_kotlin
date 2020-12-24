package com.example.moviesappmvpkotlin.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getData(@Url path:String, @QueryMap  param:HashMap<String, Any?>): Response<JsonElement>
}
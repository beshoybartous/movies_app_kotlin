package com.example.moviesappmvpkotlin.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory



private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(EndPoints.ROOT_URL)
    .build()

object MoviesApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

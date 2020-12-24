package com.example.moviesappmvpkotlin.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.squareup.moshi.Moshi
import retrofit2.Response


class NetworkManager {
    //    private val job = SupervisorJob()
//    private val coroutineScope= CoroutineScope(job+Dispatchers.Main)
    public suspend  fun <T> getData(path: String, param: HashMap<String, Any?>, classType: Class<T>): T {
        return parseResponse(MoviesApi.retrofitService.getData(path, param), classType)
    }

     fun <T> parseResponse(response: Response<JsonElement>, classType: Class<T>): T {
        try {
            response.isSuccessful.let {

                return Gson().fromJson(response.body().toString(), classType)
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}
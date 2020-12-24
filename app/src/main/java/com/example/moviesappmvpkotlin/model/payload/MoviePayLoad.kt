package com.example.moviesappmvpkotlin.model.payload

import com.google.gson.annotations.SerializedName

data class MoviePayLoad(
    @SerializedName( "api_key")
    var apiKey: String? = null,
    @SerializedName( "page")
    var page: Int? = 0
)

val MoviePayLoad.toMap: HashMap<String, Any?>
    get() = hashMapOf("api_key" to apiKey, "page" to page)

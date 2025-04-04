package com.example.idatapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val URL_BASE = "https://jsonplaceholder.typicode.com/"
    fun getRetrofit(): Retrofit
    {
        var retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
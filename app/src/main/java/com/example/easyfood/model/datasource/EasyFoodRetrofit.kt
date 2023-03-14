package com.example.easyfood.model.datasource

import com.example.easyfood.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object EasyFoodRetrofit {

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(
            BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
            )
    }

    val easyFoodRetrofit: EasyFoodApi by lazy {
        retrofit.build().create(EasyFoodApi::class.java)
    }
}
package com.example.ktapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitApiService {
    companion object {
        fun create(): RetrofitApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.103:8080/")
                .build()

            return retrofit.create(RetrofitApiService::class.java)
        }
    }
}

//NO ESTA EN USO ACTUALMENTE
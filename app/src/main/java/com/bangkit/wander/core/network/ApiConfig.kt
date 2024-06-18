package com.bangkit.wander.core.network

import com.bangkit.wander.BuildConfig
import com.bangkit.wander.core.utils.OkHttpsClientFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig{
    companion object {
        private const val baseURL: String = BuildConfig.BASE_URL
        private const val mlURL: String = BuildConfig.ML_URL
        fun <T> getApiService(service: Class<T>): T {
            val client = OkHttpsClientFactory.create()
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service)
        }
        fun <T> getMLService(service: Class<T>): T {
            val client = OkHttpsClientFactory.create()
            return Retrofit.Builder()
                .baseUrl(mlURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service)
        }
    }
}
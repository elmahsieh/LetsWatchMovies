package com.ehsieh2.letswatchtv.dataHandler

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "f30a516df866e8e39ffc019d1fcace2f"  // Ensure this is securely managed

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(API_KEY))
        .build()

    val instance: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)  // Make sure Api is your interface
    }
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url  // Ensure to use the method call

        val newHttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(newRequest)
    }
}


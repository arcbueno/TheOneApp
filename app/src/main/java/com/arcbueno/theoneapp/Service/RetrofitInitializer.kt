package com.arcbueno.theoneapp.Service

import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class RetrofitInitializer{

    val token = "UA4NRwn1vVyMSLhtm52_"

    private var client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }
    }).build()


    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://the-one-api.herokuapp.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun apiService() =  retrofit.create(APIService::class.java)

}

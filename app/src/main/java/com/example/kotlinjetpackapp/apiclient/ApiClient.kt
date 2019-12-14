package com.example.kotlinjetpackapp.apiclient

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * created by Ramanuj Kesharawani on 10/12/19
 */
class ApiClient {

    companion object{
        private const val BASE_URL="https://jsonplaceholder.typicode.com/"
        private var retrofit: Retrofit?=null

        private fun getInstance(): Retrofit {
            val builder by lazy { initOkHttpBuilder() }
            val client by lazy { builder.build()  }

            if(retrofit==null) {
                retrofit= Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }

        private fun initOkHttpBuilder(): OkHttpClient.Builder{
            val builder= OkHttpClient.Builder()
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            return builder
        }

        fun getServices():ServicesApi{
            return getInstance().create(ServicesApi::class.java)
        }
    }
}
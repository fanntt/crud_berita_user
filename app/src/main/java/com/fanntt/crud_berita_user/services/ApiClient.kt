package com.fanntt.crud_berita_user.services

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays

object ApiClient {
//    http://10.208.104.237:8080/beritaDb/getBerita.php

    private const val BASE_URL = "http://192.168.16.234/beritaDb/"

     val retrofit: BeritaServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaServices::class.java)
    }

    private val client = OkHttpClient.Builder()
        .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-type","application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    //buat method interceptor
    fun interceptor() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
//
//    //panggil product service
//    val produkService : BeritaServices by lazy {
//        retrofit.create(BeritaServices::class.java)
//    }
}
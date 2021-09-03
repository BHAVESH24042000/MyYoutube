package com.example.myyoutube.youtubeAPI

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object YoutubeAPInetworkModule {

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)


    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())

    val youtubeApi = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(YoutubeAPIinterface::class.java)
}
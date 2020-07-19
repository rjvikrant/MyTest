package com.example.android.mytest.data.network

import com.example.android.mytest.data.network.responses.FactsResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiCalls {

    @GET("facts.json")
suspend fun getListData():Response<FactsResponse>

    companion object {

        operator fun invoke(networkConnectionIntercepter: NetworkIntercepter): ApiCalls {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionIntercepter)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiCalls::class.java)
        }
    }
}
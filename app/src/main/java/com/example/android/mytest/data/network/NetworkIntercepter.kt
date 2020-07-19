package com.example.android.mytest.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.mytest.util.NoIntenetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkIntercepter (private val context: Context) : Interceptor {

    private val applicationContext =context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailabel()){

            throw NoIntenetException("Make Sure you have Active data connection")
        }

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailabel():Boolean{
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork).apply {
                result= true
            }
        }

 return result
    }
}
package com.example.android.mytest.data.network

import android.util.Log
import com.example.android.mytest.util.ApiExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequests {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {

            return response.body()!!
        } else {

            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {

                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    Log.e("exception", e.message)
                }
                message.append("\n")
            }
            message.append("Error Code : ${response.code()}")
            throw ApiExceptions(message.toString())
        }
    }
}
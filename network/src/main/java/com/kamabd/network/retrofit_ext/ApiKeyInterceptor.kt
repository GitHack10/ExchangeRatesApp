package com.kamabd.network.retrofit_ext

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val originalRequest = chain.request()
        if (apiKey.trim().isEmpty()) {
            return chain.proceed(originalRequest)
        }
        val newRequest = originalRequest.addApiKey()
        return chain.proceed(newRequest)
    }

    private fun Request.addApiKey(): Request {
        return newBuilder()
            .addHeader("apikey", apiKey)
            .build()
    }
}
package com.example.imdbmoviesapp.core

import com.example.imdbmoviesapp.core.constants.AppConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", AppConstants.API_KEY)
            .addQueryParameter("language", AppConstants.LANGUAGE)
            .build()
        
        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()
        return chain.proceed(newRequest)
    }
}
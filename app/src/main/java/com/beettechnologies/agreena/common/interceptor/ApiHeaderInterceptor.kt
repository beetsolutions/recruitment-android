package com.beettechnologies.agreena.common.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiHeaderInterceptor  : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_VALUE)
            .addHeader(API_KEY_HEADER, API_KEY_VALUE)
        return chain.proceed(newRequest.build())
    }

    companion object {
        const val CONTENT_TYPE_HEADER = "Content-Type"
        const val CONTENT_TYPE_VALUE = "application/json"
        const val API_KEY_HEADER = "API-KEY"
        const val API_KEY_VALUE = "FIELDMARGIN-TECH-TEST"
    }
}

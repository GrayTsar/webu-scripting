package com.graytsar.definition

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestOriginal = chain.request()
        val request = requestOriginal.newBuilder()
            .addHeader(
                "user-agent",
                "Mozilla/5.0 (Linux; Android 13; SM-A205U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Mobile Safari/537.36")
            .addHeader(
                "accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
            )
            .addHeader(
                "accept-language",
                "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7"
            )
            .build()

        return chain.proceed(request)
    }
}
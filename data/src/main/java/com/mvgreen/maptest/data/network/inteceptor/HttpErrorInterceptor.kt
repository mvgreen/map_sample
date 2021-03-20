package com.mvgreen.maptest.data.network.inteceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpErrorInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO
        return chain.proceed(chain.request())
    }

}
package com.mvgreen.maptest.data.network.factory

import com.mvgreen.maptest.data.network.inteceptor.HttpErrorInterceptor
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Converter
import javax.inject.Inject

class GoogleMapsApiFactory @Inject constructor(
    httpErrorInterceptor: HttpErrorInterceptor,
    authenticator: Authenticator,
    vararg converters: Converter.Factory
): ApiFactory(
    NetworkConstants.GoogleMapsUrl(),
    OkHttpClient.Builder()
        .addInterceptor(httpErrorInterceptor)
        .authenticator(authenticator)
        .build(),
    *converters
)
package com.mvgreen.maptest.data.network.geocode.api

import com.mvgreen.maptest.data.network.factory.NetworkConstants.GoogleMapsConstants.API_KEY
import com.mvgreen.maptest.data.network.geocode.entity.GeocodeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeApi {

    @GET("geocode/json")
    fun geocodeAddress(
        @Query("address") address: String,
        @Query("language") language: String = "ru",
        @Query("key") key: String = API_KEY,
    ): Single<GeocodeResponse>

}
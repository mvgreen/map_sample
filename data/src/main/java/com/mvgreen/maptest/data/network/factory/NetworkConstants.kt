package com.mvgreen.maptest.data.network.factory

import com.mvgreen.maptest.data.BuildConfig

object NetworkConstants {

    open class ServerUrl(val url: String)

    class GoogleMapsUrl : ServerUrl("https://maps.googleapis.com/maps/api/")

    object GoogleMapsConstants {
        const val API_KEY = BuildConfig.GMAPS_KEY

        const val STATUS_OK = "OK"
        const val STATUS_ZERO = "ZERO_RESULTS"
        const val STATUS_LIMIT = "OVER_QUERY_LIMIT"
    }

}
package com.mvgreen.maptest.data.network.geocode.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeocodeResponse(
    @Json(name = "results")
    val results: List<Place>,
    @Json(name = "status")
    val status: String
)

@JsonClass(generateAdapter = true)
data class Place(
    @Json(name = "formatted_address")
    val formattedAddress: String,
    @Json(name = "geometry")
    val geometry: Geometry
)

@JsonClass(generateAdapter = true)
data class Geometry(
    @Json(name = "location")
    val location: LatLng
)

@JsonClass(generateAdapter = true)
data class LatLng(
    @Json(name = "lat")
    val latitude: Double,
    @Json(name = "lng")
    val longitude: Double
)

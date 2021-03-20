package com.mvgreen.maptest.domain.entity

data class Geotag(
    val name: String,
    val address: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)
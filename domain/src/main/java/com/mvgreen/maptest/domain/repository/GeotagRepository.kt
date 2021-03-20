package com.mvgreen.maptest.domain.repository

import com.mvgreen.maptest.domain.entity.Geotag
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface GeotagRepository {

    fun getGeotagList(): Observable<List<Geotag>>

    fun addGeotag(geotag: Geotag): Completable

    fun validateAndSupplementGeotag(geotag: Geotag): Single<Geotag>
}
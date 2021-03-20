package com.mvgreen.maptest.domain.usecase

import com.mvgreen.maptest.domain.entity.Geotag
import io.reactivex.Completable
import io.reactivex.Observable

interface GeotagUseCase {

    fun loadGeotagList(): Observable<List<Geotag>>

    fun addGeotag(geotag: Geotag): Completable
}
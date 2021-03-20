package com.mvgreen.maptest.data.usecase

import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.domain.repository.GeotagRepository
import com.mvgreen.maptest.domain.usecase.GeotagUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GeotagUseCaseImpl
@Inject constructor(
    private val geotagRepository: GeotagRepository
) : GeotagUseCase {

    override fun loadGeotagList(): Observable<List<Geotag>> {
        return geotagRepository
            .getGeotagList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addGeotag(geotag: Geotag): Completable {
        return geotagRepository
            .validateAndSupplementGeotag(geotag)
            .flatMapCompletable { geotagRepository.addGeotag(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
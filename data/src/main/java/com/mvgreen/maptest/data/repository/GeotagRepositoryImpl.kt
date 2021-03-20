package com.mvgreen.maptest.data.repository

import com.mvgreen.maptest.data.db.dao.GeotagDao
import com.mvgreen.maptest.data.db.entity.DbGeotag
import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.domain.repository.GeotagRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class GeotagRepositoryImpl
@Inject constructor(
    private val geotagDao: GeotagDao
) : GeotagRepository {

    override fun getGeotagList(): Observable<List<Geotag>> {
        return geotagDao
            .getGeotagList()
            .transformToGeotag()
    }

    override fun addGeotag(geotag: Geotag): Completable {
        return with(geotag) {
            val id = UUID.randomUUID().toString()
            geotagDao.insert(DbGeotag(id, name, address, latitude, longitude))
        }
    }

    override fun validateAndSupplementGeotag(geotag: Geotag): Single<Geotag> {
        TODO("Network")
    }

    private fun Observable<List<DbGeotag>>.transformToGeotag(): Observable<List<Geotag>> {
        return map { list ->
            list.map { item -> Geotag(item.name, item.address, item.latitude, item.longitude) }
        }
    }
}

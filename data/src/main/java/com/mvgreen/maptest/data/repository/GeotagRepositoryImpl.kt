package com.mvgreen.maptest.data.repository

import com.mvgreen.maptest.data.db.dao.GeotagDao
import com.mvgreen.maptest.data.db.entity.DbGeotag
import com.mvgreen.maptest.data.network.factory.NetworkConstants.GoogleMapsConstants.STATUS_LIMIT
import com.mvgreen.maptest.data.network.factory.NetworkConstants.GoogleMapsConstants.STATUS_OK
import com.mvgreen.maptest.data.network.factory.NetworkConstants.GoogleMapsConstants.STATUS_ZERO
import com.mvgreen.maptest.data.network.geocode.api.GeocodeApi
import com.mvgreen.maptest.data.network.geocode.entity.GeocodeResponse
import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.domain.exception.GeocodeGeneralException
import com.mvgreen.maptest.domain.exception.GeocodeLimitException
import com.mvgreen.maptest.domain.exception.GeocodeZeroResultsException
import com.mvgreen.maptest.domain.repository.GeotagRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class GeotagRepositoryImpl
@Inject constructor(
    private val geotagDao: GeotagDao,
    private val geocodeApi: GeocodeApi
) : GeotagRepository {

    override fun getGeotagList(): Observable<List<Geotag>> {
        return geotagDao
            .getGeotagList()
            .fromDb()
    }

    override fun addGeotag(geotag: Geotag): Completable {
        return with(geotag) {
            val id = UUID.randomUUID().toString()
            // Latitude and longitude are already loaded
            geotagDao.insert(DbGeotag(id, name, address, latitude!!, longitude!!))
        }
    }

    override fun validateAndSupplementGeotag(geotag: Geotag): Single<Geotag> {
        return geocodeApi
            .geocodeAddress(geotag.address)
            .fromNet(geotag)
    }

    private fun Observable<List<DbGeotag>>.fromDb(): Observable<List<Geotag>> {
        return map { list ->
            list.map { item -> Geotag(item.name, item.address, item.latitude, item.longitude) }
        }
    }

    private fun Single<GeocodeResponse>.fromNet(userInput: Geotag): Single<Geotag> {
        return map {
            when (it.status) {
                STATUS_OK -> {
                    val firstResult = it.results.first()
                    val lat = firstResult.geometry.location.latitude
                    val lng = firstResult.geometry.location.longitude
                    return@map Geotag(userInput.name, firstResult.formattedAddress, lat, lng)
                }
                STATUS_ZERO -> throw GeocodeZeroResultsException()
                STATUS_LIMIT -> throw GeocodeLimitException()
                else -> throw GeocodeGeneralException()
            }
        }
    }

}

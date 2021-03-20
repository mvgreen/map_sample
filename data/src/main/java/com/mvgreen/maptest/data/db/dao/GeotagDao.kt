package com.mvgreen.maptest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mvgreen.maptest.data.db.entity.DbGeotag
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface GeotagDao {

    @Insert
    fun insert(geotag: DbGeotag): Completable

    @Query("select * from geotag")
    fun getGeotagList(): Observable<List<DbGeotag>>

}
package com.mvgreen.maptest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvgreen.maptest.data.db.dao.GeotagDao
import com.mvgreen.maptest.data.db.entity.DbGeotag

@Database(entities = [DbGeotag::class], version = 1)
abstract class GeotagDatabase : RoomDatabase() {

    abstract fun geotagDao(): GeotagDao

}
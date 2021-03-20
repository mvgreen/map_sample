package com.mvgreen.maptest.internal.di.module

import android.content.Context
import androidx.room.Room
import com.mvgreen.maptest.data.db.GeotagDatabase
import com.mvgreen.maptest.data.db.dao.GeotagDao
import com.mvgreen.maptest.data.network.factory.GoogleMapsApiFactory
import com.mvgreen.maptest.data.network.geocode.api.GeocodeApi
import com.mvgreen.maptest.data.network.inteceptor.HttpErrorInterceptor
import com.mvgreen.maptest.data.repository.GeotagRepositoryImpl
import com.mvgreen.maptest.data.usecase.GeotagUseCaseImpl
import com.mvgreen.maptest.domain.repository.GeotagRepository
import com.mvgreen.maptest.domain.usecase.GeotagUseCase
import com.mvgreen.maptest.internal.di.scope.ApplicationScope
import com.mvgreen.maptest.ui.main.viewmodel.MainMenuViewModel
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
internal class AppModule {

    @Provides
    @ApplicationScope
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @ApplicationScope
    fun provideGeocodeApi(moshi: Moshi): GeocodeApi = GoogleMapsApiFactory(
        HttpErrorInterceptor(),
        MoshiConverterFactory.create(moshi)
    ).create(GeocodeApi::class.java)

    @Provides
    @ApplicationScope
    fun provideDb(
        applicationContext: Context
    ): GeotagDatabase = Room
        .databaseBuilder(applicationContext, GeotagDatabase::class.java, "geotag_db")
        .build()

    @Provides
    @ApplicationScope
    fun provideGeotagDao(
        db: GeotagDatabase
    ): GeotagDao = db.geotagDao()

    @Provides
    @ApplicationScope
    fun provideGeotagRepository(
        geotagDao: GeotagDao,
        geocodeApi: GeocodeApi
    ): GeotagRepository = GeotagRepositoryImpl(geotagDao, geocodeApi)

    @Provides
    @ApplicationScope
    fun provideGeotagUseCase(
        repository: GeotagRepository
    ): GeotagUseCase = GeotagUseCaseImpl(repository)

}
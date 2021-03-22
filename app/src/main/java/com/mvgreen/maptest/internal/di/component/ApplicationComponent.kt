package com.mvgreen.maptest.internal.di.component

import android.content.Context
import com.mvgreen.maptest.data.network.geocode.api.GeocodeApi
import com.mvgreen.maptest.domain.repository.GeotagRepository
import com.mvgreen.maptest.domain.usecase.GeotagUseCase
import com.mvgreen.maptest.internal.di.module.AppModule
import com.mvgreen.maptest.internal.di.scope.ApplicationScope
import com.mvgreen.maptest.ui.main.viewmodel.MainMenuViewModel
import com.mvgreen.maptest.ui.map.viewmodel.MapViewModel
import com.squareup.moshi.Moshi
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@ApplicationScope
internal interface ApplicationComponent {

    fun moshi(): Moshi

    fun geocodeApi(): GeocodeApi

    fun geotagRepository(): GeotagRepository

    fun geotagUseCase(): GeotagUseCase

    fun mainMenuViewModel(): MainMenuViewModel

    fun mapViewModel(): MapViewModel

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(appContext: Context): Builder

        fun build(): ApplicationComponent

    }

}
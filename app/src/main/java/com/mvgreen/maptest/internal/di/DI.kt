package com.mvgreen.maptest.internal.di

import android.content.Context
import com.mvgreen.maptest.internal.di.component.ApplicationComponent
import com.mvgreen.maptest.internal.di.component.DaggerApplicationComponent

internal object DI {

    lateinit var appComponent: ApplicationComponent
        private set


    fun init(appContext: Context) {
        appComponent = DaggerApplicationComponent.builder().context(appContext).build()
    }

}
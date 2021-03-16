package com.mvgreen.maptest.ui

import android.app.Application
import com.mvgreen.maptest.internal.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DI.init(this)
    }

}
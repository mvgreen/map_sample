package com.mvgreen.maptest.internal.di.component

import android.content.Context
import com.mvgreen.maptest.internal.di.module.AppModule
import com.mvgreen.maptest.internal.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@ApplicationScope
internal interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(appContext: Context): Builder

        fun build(): ApplicationComponent

    }

}
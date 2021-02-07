package com.kalay.themoviedb.application

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.support.DaggerApplication

open class Application : DaggerApplication() {

    private val applicationInjector =
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()


    override fun applicationInjector() = applicationInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}


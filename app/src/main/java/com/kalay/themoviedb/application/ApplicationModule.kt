@file:Suppress("unused")

package com.kalay.themoviedb.application

import android.app.Application
import android.content.Context
import com.kalay.component.modules.RecyclerAdapterModule
import com.kalay.themoviedb.ioc.builder.ActivityBuilderModule
import com.kalay.core.ioc.modules.NetworkModule
import com.kalay.core.ioc.modules.SystemServiceModule
import com.kalay.core.ioc.qualifiers.ApplicationContext
import com.kalay.data.database.TheMovieLocalDbModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Module(
    includes = [
        NetworkModule::class, SystemServiceModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class, RecyclerAdapterModule::class, TheMovieLocalDbModule::class]
)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: com.kalay.themoviedb.application.Application): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context
}

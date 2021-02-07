package com.kalay.themoviedb.ioc.modules

import android.content.Context
import androidx.room.Room
import com.kalay.core.BuildConfig
import com.kalay.themoviedb.ioc.database.AppDatabase
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.themoviedb.application.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class TheMovieLocalDbModule {

    @Singleton
    @Provides
    fun provideDb(app: Context): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, BuildConfig.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieCardDao(db: AppDatabase): MovieCardDBDao {
        return db.movieCardDbDao()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}

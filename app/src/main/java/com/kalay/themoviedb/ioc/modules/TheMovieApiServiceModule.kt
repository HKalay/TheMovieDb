package com.kalay.themoviedb.ioc.modules

import com.kalay.core.ioc.scopes.ActivityScope
import com.kalay.data.request.IHomePageApi
import com.kalay.data.request.ISearchPageApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class TheMovieApiServiceModule {

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideIHomeApi(retrofit: Retrofit): IHomePageApi =
            retrofit.create(IHomePageApi::class.java)

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideISearchApi(retrofit: Retrofit): ISearchPageApi =
            retrofit.create(ISearchPageApi::class.java)

    }
}

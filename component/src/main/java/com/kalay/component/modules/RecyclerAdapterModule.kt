package com.kalay.component.modules

import com.kalay.component.TheMovieRecycleriewAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerAdapterModule {

    @Provides
    fun provideAdapter(): TheMovieRecycleriewAdapter {
        return TheMovieRecycleriewAdapter()
    }
}
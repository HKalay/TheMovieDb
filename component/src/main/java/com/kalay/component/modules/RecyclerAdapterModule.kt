package com.kalay.component.modules

import com.kalay.component.TheMovieRecyclerviewAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerAdapterModule {

    @Provides
    fun provideAdapter(): TheMovieRecyclerviewAdapter {
        return TheMovieRecyclerviewAdapter()
    }
}
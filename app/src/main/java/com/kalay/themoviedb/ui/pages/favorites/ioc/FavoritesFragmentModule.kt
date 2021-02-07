package com.kalay.themoviedb.ui.pages.favorites.ioc

import androidx.fragment.app.Fragment
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.Scheduler
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.themoviedb.ioc.keys.FragmentViewModelKey
import com.kalay.themoviedb.ui.base.fragment.BaseViewModelFragmentModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.favorites.FavoritesFragment
import com.kalay.themoviedb.ui.pages.favorites.repository.FavoritesFragmentLocalData
import com.kalay.themoviedb.ui.pages.favorites.repository.FavoritesFragmentRepository
import com.kalay.themoviedb.ui.pages.favorites.viewmodel.FavoritesFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class FavoritesFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: FavoritesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(FavoritesFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: FavoritesFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {


        @Provides
        @FragmentScope
        @JvmStatic
        fun favoritesFragmentLocalData(
            movieCardDao: MovieCardDBDao
        ) =
            FavoritesFragmentLocalData(movieCardDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun favoritesFragmentRepository(
            local:FavoritesFragmentLocalData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = FavoritesFragmentRepository(local, scheduler, compositeDisposable)
    }
}
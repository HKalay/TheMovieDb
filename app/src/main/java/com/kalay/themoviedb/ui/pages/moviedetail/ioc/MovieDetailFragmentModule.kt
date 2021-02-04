package com.kalay.themoviedb.ui.pages.moviedetail.ioc

import androidx.fragment.app.Fragment
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.Scheduler
import com.kalay.data.request.IMovieDetailPageApi
import com.kalay.themoviedb.ioc.keys.FragmentViewModelKey
import com.kalay.themoviedb.ui.base.fragment.BaseViewModelFragmentModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.moviedetail.MovieDetailFragment
import com.kalay.themoviedb.ui.pages.moviedetail.repository.MovieDetailFragmentRemoteData
import com.kalay.themoviedb.ui.pages.moviedetail.repository.MovieDetailFragmentRepository
import com.kalay.themoviedb.ui.pages.moviedetail.viewmodel.MovieDetailFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class MovieDetailFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: MovieDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(MovieDetailFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: MovieDetailFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun movieDetailFragmentRemoteData(apiInterface: IMovieDetailPageApi) =
            MovieDetailFragmentRemoteData(
                apiInterface
            )

        @Provides
        @FragmentScope
        @JvmStatic
        fun movieDetailFragmentRepository(
            remote: MovieDetailFragmentRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) =
            MovieDetailFragmentRepository(
                remote,
                scheduler,
                compositeDisposable
            )
    }
}
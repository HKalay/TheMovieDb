package com.kalay.themoviedb.ui.pages.search.ioc

import androidx.fragment.app.Fragment
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.Scheduler
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.data.request.ISearchPageApi
import com.kalay.themoviedb.ioc.keys.FragmentViewModelKey
import com.kalay.themoviedb.ui.base.fragment.BaseViewModelFragmentModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.search.SearchFragment
import com.kalay.themoviedb.ui.pages.search.repository.SearchFragmentLocalData
import com.kalay.themoviedb.ui.pages.search.repository.SearchFragmentRemoteData
import com.kalay.themoviedb.ui.pages.search.repository.SearchFragmentRepository
import com.kalay.themoviedb.ui.pages.search.viewmodel.SearchFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class SearchFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: SearchFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(SearchFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: SearchFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun searchFragmentRemoteData(apiInterface: ISearchPageApi) =
            SearchFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun searchFragmentLocalData(
            movieCardDao: MovieCardDBDao
        ) =
            SearchFragmentLocalData(movieCardDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun searchFragmentRepository(
            remote: SearchFragmentRemoteData,
            local: SearchFragmentLocalData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = SearchFragmentRepository(remote,local, scheduler, compositeDisposable)
    }
}
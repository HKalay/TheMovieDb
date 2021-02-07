package com.kalay.themoviedb.ui.pages.home.fragment.ioc

import androidx.fragment.app.Fragment
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.Scheduler
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.data.request.IHomePageApi
import com.kalay.themoviedb.ioc.keys.FragmentViewModelKey
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentRemoteData
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentRepository
import com.kalay.themoviedb.ui.pages.home.fragment.viewmodel.HomeFragmentViewModel
import com.kalay.themoviedb.ui.base.fragment.BaseViewModelFragmentModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.home.fragment.HomeFragment
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentLocalData
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class HomeFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(HomeFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: HomeFragmentViewModel): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun homeFragmentRemoteData(apiInterface: IHomePageApi) =
            HomeFragmentRemoteData(apiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun homeFragmentLocalData(
            movieCardDao: MovieCardDBDao
        ) =
            HomeFragmentLocalData(movieCardDao)

        @Provides
        @FragmentScope
        @JvmStatic
        fun homeFragmentRepository(
            remote: HomeFragmentRemoteData,
            local: HomeFragmentLocalData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ) = HomeFragmentRepository(remote,local, scheduler, compositeDisposable)
    }
}
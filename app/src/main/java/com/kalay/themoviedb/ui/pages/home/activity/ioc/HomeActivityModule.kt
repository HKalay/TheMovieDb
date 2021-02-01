package com.kalay.themoviedb.ui.pages.home.activity.ioc

import androidx.appcompat.app.AppCompatActivity
import com.kalay.themoviedb.ui.pages.home.activity.viewmodel.HomeActivityViewModel
import com.kalay.core.ioc.scopes.ActivityScope
import com.kalay.themoviedb.ioc.builder.FragmentBuilderModule
import com.kalay.themoviedb.ioc.keys.ActivityViewModelKey
import com.kalay.themoviedb.ioc.modules.TheMovieApiServiceModule
import com.kalay.themoviedb.ui.base.activity.BaseActivityModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseActivityViewModel
import com.kalay.themoviedb.ui.pages.home.activity.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(
    includes = [
        BaseActivityModule::class,
        FragmentBuilderModule::class,
        TheMovieApiServiceModule::class]
)
abstract class HomeActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: HomeActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(HomeActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: HomeActivityViewModel): BaseActivityViewModel

}

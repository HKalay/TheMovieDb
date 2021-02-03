package com.kalay.themoviedb.ui.pages.detail.ioc

import androidx.appcompat.app.AppCompatActivity
import com.kalay.core.ioc.scopes.ActivityScope
import com.kalay.themoviedb.ioc.builder.FragmentBuilderModule
import com.kalay.themoviedb.ioc.keys.ActivityViewModelKey
import com.kalay.themoviedb.ioc.modules.TheMovieApiServiceModule
import com.kalay.themoviedb.ui.base.activity.BaseActivityModule
import com.kalay.themoviedb.ui.base.viewmodel.BaseActivityViewModel
import com.kalay.themoviedb.ui.pages.detail.DetailActivity
import com.kalay.themoviedb.ui.pages.detail.viewmodel.DetailActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(
	includes = [
		BaseActivityModule::class,
		FragmentBuilderModule::class,
		TheMovieApiServiceModule::class]
)
abstract class DetailActivityModule {

	@Binds
	@ActivityScope
	abstract fun bindActivity(activity: DetailActivity): AppCompatActivity

	@Binds
	@IntoMap
	@ActivityViewModelKey(DetailActivityViewModel::class)
	@ActivityScope
	abstract fun bindViewModel(activityDetailViewModel: DetailActivityViewModel): BaseActivityViewModel
}

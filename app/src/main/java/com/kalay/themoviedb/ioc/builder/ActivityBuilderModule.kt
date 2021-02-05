package com.kalay.themoviedb.ioc.builder

import com.kalay.core.ioc.scopes.ActivityScope
import com.kalay.themoviedb.ui.pages.detail.activity.DetailActivity
import com.kalay.themoviedb.ui.pages.detail.activity.ioc.DetailActivityModule
import com.kalay.themoviedb.ui.pages.home.activity.HomeActivity
import com.kalay.themoviedb.ui.pages.home.activity.ioc.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [HomeActivityModule::class])
	internal abstract fun bindHomeActivity(): HomeActivity

	@ActivityScope
	@ContributesAndroidInjector(modules = [DetailActivityModule::class])
	internal abstract fun bindDetailActivity(): DetailActivity

}

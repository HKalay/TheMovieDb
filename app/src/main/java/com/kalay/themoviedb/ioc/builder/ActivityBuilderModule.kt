package com.kalay.themoviedb.ioc.builder

import com.kalay.core.ioc.scopes.ActivityScope
import com.kalay.themoviedb.ui.pages.home.activity.HomeActivity
import com.kalay.themoviedb.ui.pages.home.activity.ioc.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [HomeActivityModule::class])
	internal abstract fun bindSplashActivity(): HomeActivity

}

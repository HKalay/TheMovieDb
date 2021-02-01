package com.kalay.themoviedb.ioc.builder

import com.kalay.core.ioc.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
	/*@ContributesAndroidInjector(modules = [TestFragmentModule::class])
	@FragmentScope
	abstract fun contributeHomeFragment(): TestFragment*/
}

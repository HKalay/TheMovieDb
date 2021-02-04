package com.kalay.themoviedb.ioc.builder

import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.themoviedb.ui.pages.favorites.FavoritesFragment
import com.kalay.themoviedb.ui.pages.favorites.ioc.FavoritesFragmentModule
import com.kalay.themoviedb.ui.pages.home.fragment.HomeFragment
import com.kalay.themoviedb.ui.pages.home.fragment.ioc.HomeFragmentModule
import com.kalay.themoviedb.ui.pages.moviedetail.MovieDetailFragment
import com.kalay.themoviedb.ui.pages.moviedetail.ioc.MovieDetailFragmentModule
import com.kalay.themoviedb.ui.pages.search.SearchFragment
import com.kalay.themoviedb.ui.pages.search.ioc.SearchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    @FragmentScope
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    @FragmentScope
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    @FragmentScope
    abstract fun contributeMyFavoriteFragment(): FavoritesFragment

    @ContributesAndroidInjector(modules = [MovieDetailFragmentModule::class])
    @FragmentScope
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}

package com.kalay.themoviedb.ui.pages.favorites.viewmodel

import android.content.SharedPreferences
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.favorites.FavoritesFragmentGetData
import com.kalay.themoviedb.ui.pages.favorites.repository.FavoritesFragmentRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class FavoritesFragmentViewModel @Inject constructor(
    val repository: FavoritesFragmentRepository,
    val sharedPreferences: SharedPreferences
) : BaseFragmentViewModel() {

    private val favoritesItemList = mutableListOf<DisplayItem>()
    private val compositeDisposable = CompositeDisposable()
    private val favoritesFragmentGetData =
        FavoritesFragmentGetData(
            compositeDisposable
        )

}

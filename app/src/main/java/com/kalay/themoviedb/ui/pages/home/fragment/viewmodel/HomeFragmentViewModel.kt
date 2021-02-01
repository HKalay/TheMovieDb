package com.kalay.themoviedb.ui.pages.home.fragment.viewmodel

import android.content.SharedPreferences
import com.kalay.themoviedb.ui.pages.home.fragment.HomeFragmentGetData
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentRepository
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class HomeFragmentViewModel @Inject constructor(
    val repository: HomeFragmentRepository,
    val sharedPreferences: SharedPreferences
) : BaseFragmentViewModel() {

    private val homeItemList = mutableListOf<DisplayItem>()
    private val compositeDisposable = CompositeDisposable()
    private val homeFragmentGetData =
        HomeFragmentGetData(
            compositeDisposable
        )

}

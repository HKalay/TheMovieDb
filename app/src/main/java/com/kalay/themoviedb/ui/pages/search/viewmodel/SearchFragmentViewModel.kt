package com.kalay.themoviedb.ui.pages.search.viewmodel

import android.content.SharedPreferences
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.search.SearchFragmentGetData
import com.kalay.themoviedb.ui.pages.search.repository.SearchFragmentRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class SearchFragmentViewModel @Inject constructor(
    val repository: SearchFragmentRepository,
    val sharedPreferences: SharedPreferences
) : BaseFragmentViewModel() {

    private val searchItemList = mutableListOf<DisplayItem>()
    private val compositeDisposable = CompositeDisposable()
    private val searchFragmentGetData =
        SearchFragmentGetData(
            compositeDisposable
        )

}

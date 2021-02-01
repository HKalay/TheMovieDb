package com.kalay.themoviedb.ui.pages.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.kalay.component.TheMovieRecycleriewAdapter
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.search.viewmodel.SearchFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchFragment : BaseDataFetchFragment<SearchFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var adapterPageList: TheMovieRecycleriewAdapter


    override val viewModelClass = SearchFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

    }
}


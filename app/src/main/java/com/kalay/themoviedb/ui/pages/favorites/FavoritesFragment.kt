package com.kalay.themoviedb.ui.pages.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.favorites.viewmodel.FavoritesFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FavoritesFragment : BaseDataFetchFragment<FavoritesFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var adapterPageList: TheMovieRecyclerviewAdapter


    override val viewModelClass = FavoritesFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_favorites

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

    }
}


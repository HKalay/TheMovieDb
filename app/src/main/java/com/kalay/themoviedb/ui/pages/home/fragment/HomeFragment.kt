package com.kalay.themoviedb.ui.pages.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.kalay.component.TheMovieRecycleriewAdapter
import com.kalay.core.extensions.addTo
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visibile
import com.kalay.core.networking.DataFetchResult
import com.kalay.data.zipdto.HomePageAllRequestZipDTO
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.home.fragment.viewmodel.HomeFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseDataFetchFragment<HomeFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable
    var homePageDisposable: Disposable? = null

    @Inject
    lateinit var adapterPageList: TheMovieRecycleriewAdapter


    override val viewModelClass = HomeFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        homePageDisposable?.let {
            it.dispose()
            compositeDisposable.clear()
            compositeDisposable.dispose()
        }
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

        bindHomePage()

    }

    private fun bindHomePage(){
        rvHomePage.setup(adapter = adapterPageList.getAdapter())

        viewModel.getHomePageData()

        if (homePageDisposable == null) {
            homePageDisposable = viewModel.homePageAllRequestZip.subscribe {
                when (it) {
                    is DataFetchResult.Success -> {
                        val zipDTO = it.data as HomePageAllRequestZipDTO
                        zipDTO.homePageResponse?.let { homeData ->
                            adapterPageList.getAdapter().clear()
                            adapterPageList.getAdapter().updateAllItems(homeData)
                        }
                        pbHomePage.gone()
                    }
                    is DataFetchResult.Progress -> {
                        pbHomePage.visibile()
                    }
                    is DataFetchResult.Failure -> {
                        pbHomePage.gone()
                    }
                }
            }
        }
        homePageDisposable?.addTo(compositeDisposable)
    }
}


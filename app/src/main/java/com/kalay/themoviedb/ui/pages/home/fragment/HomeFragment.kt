package com.kalay.themoviedb.ui.pages.home.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kalay.component.TheMovieRecycleriewAdapter
import com.kalay.component.ui.slider.SliderListDTO
import com.kalay.core.enums.PageType
import com.kalay.core.enums.ParcelableData
import com.kalay.core.extensions.*
import com.kalay.core.networking.DataFetchResult
import com.kalay.data.zipdto.HomePageAllRequestZipDTO
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.common.navigation.NavigationManager
import com.kalay.themoviedb.ui.pages.detail.DetailActivity
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        adapterPageListClick()
    }

    private fun getHomePageDate() {
        viewModel.getUpComingMovieData()
        viewModel.getTopRatedMovieData()
        viewModel.getPopularMovieData(1)
    }

    private fun bindHomePage() {
        rvHomePage.setup(
            adapter = adapterPageList.getAdapter()
        )

        getHomePageDate()

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
                        rvHomePage.visibile()
                    }
                    is DataFetchResult.Progress -> {
                        pbHomePage.visibile()
                    }
                    is DataFetchResult.Failure -> {
                        pbHomePage.gone()
                        rvHomePage.gone()
                    }
                }
            }
        }
        homePageDisposable?.addTo(compositeDisposable)
    }

    private fun adapterPageListClick() {
        adapterPageList.getAdapter().itemClickListener = { item, position ->
            when (item) {
                is SliderListDTO -> {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(ParcelableData.SLIDER_POSITION.toString(), position)
                    intent.putExtra(ParcelableData.SLIDER_LIST.toString(), item)
                    intent.putExtra(
                        ParcelableData.PAGE_TYPE.toString(),
                        PageType.Slider.toString()
                    )
                    startActivity(intent)
                }
                else -> {
                    NavigationManager().navigate(model = item, context = requireContext())
                }
            }
        }
    }
}


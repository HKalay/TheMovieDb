package com.kalay.themoviedb.ui.pages.home.fragment.viewmodel

import android.content.SharedPreferences
import com.kalay.component.helpers.horizontalrcycler.HorizontalRecyclerDTO
import com.kalay.component.ui.categorytitle.CategoryTitle
import com.kalay.themoviedb.ui.pages.home.fragment.HomeFragmentGetData
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentRepository
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.HomePageResponse
import com.kalay.data.zipdto.HomePageAllRequestZipDTO
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
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

    val homePageAllRequestZip: Observable<DataFetchResult<*>> =
        Observable.zip(repository.upComingMovieDataResult.hide(),
            repository.topRatedMovieDataResult.hide(),
            repository.popularMovieDataResult.hide(),
            Function3<DataFetchResult<HomePageResponse>, DataFetchResult<HomePageResponse>, DataFetchResult<HomePageResponse>, DataFetchResult<*>> { zipUpComingResponse, zipTopRatedResponse, zipPopularResponse ->
                setHomePageList(zipUpComingResponse, zipTopRatedResponse, zipPopularResponse)
            })

    private fun setHomePageList(
        upComingResponse: DataFetchResult<HomePageResponse>,
        topRatedResponse: DataFetchResult<HomePageResponse>,
        popularResponse: DataFetchResult<HomePageResponse>
    ): DataFetchResult<*> {

        homeItemList.clear()

        // Upcoming Data
        homeFragmentGetData.getSliderData(upComingResponse)
            ?.let {
                it.let {
                    homeItemList.add(CategoryTitle(categoryTitle = "Upcoming"))
                    homeItemList.add(it)
                }

            }

        // Top Rated Data
        homeFragmentGetData.getTopRatedData(topRatedResponse)
            ?.let {
                it.let {
                    homeItemList.add(CategoryTitle(categoryTitle = "Top Rated"))
                    homeItemList.addAll(
                        listOf(HorizontalRecyclerDTO(it, false))
                    )
                }
            }

        // Popular Data
        homeFragmentGetData.getTPopularData(popularResponse)
            ?.let {
                it.let {
                    homeItemList.add(CategoryTitle(categoryTitle = "Popular"))
                    homeItemList.addAll(it)
                }
            }

        return DataFetchResult.success(HomePageAllRequestZipDTO(homeItemList))
    }

    fun getHomePageData() {
        repository.getUpComingMovieData()
        repository.getTopRatedMovieData()
        repository.getPopularMovieData()
    }

}

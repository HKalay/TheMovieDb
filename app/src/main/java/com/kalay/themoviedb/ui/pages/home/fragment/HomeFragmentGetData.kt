package com.kalay.themoviedb.ui.pages.home.fragment

import com.kalay.component.ui.slider.SliderDTO
import com.kalay.component.ui.slider.SliderListDTO
import com.kalay.component.ui.carousel.MovieCarouselDTO
import com.kalay.component.ui.categorytitle.CategoryTitle
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.extensions.addTo
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.HomePageResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class HomeFragmentGetData(
    private val compositeDisposable: CompositeDisposable
) {

    // SLIDER DATA
    fun getSliderData(item: DataFetchResult<HomePageResponse>?): MutableList<DisplayItem>? {
        val sliderList = arrayListOf<SliderDTO>()
        val sliderListDTO = arrayListOf<DisplayItem>()

        when (item) {
            is DataFetchResult.Success -> {
                item.data.results.let { _resultsList ->
                    Observable.fromIterable(_resultsList).subscribe { _results ->
                        sliderList.add(
                            SliderDTO(
                                results = _results
                            )
                        )
                    }.addTo(compositeDisposable)
                }
            }
            else -> {

            }
        }

        if (sliderList.size != 0) {
            sliderListDTO.add(CategoryTitle(categoryTitle = "Upcoming"))
            sliderListDTO.add(SliderListDTO(sliderList = sliderList))
        }
        return sliderListDTO
    }

    // TOP RATED/CAROUSEL DATA
    fun getTopRatedData(item: DataFetchResult<HomePageResponse>?): MutableList<DisplayItem>? {
        val topRatedList = arrayListOf<DisplayItem>()

        when (item) {
            is DataFetchResult.Success -> {
                item.data.results.let { _resultsList ->
                    Observable.fromIterable(_resultsList).subscribe { _results ->
                        topRatedList.add(
                            MovieCarouselDTO(
                                results = _results
                            )
                        )
                    }.addTo(compositeDisposable)
                }
            }
            else -> {

            }
        }

        return topRatedList
    }

    // POPULAR DATA
    fun getPopularData(item: DataFetchResult<HomePageResponse>?): MutableList<DisplayItem>? {
        val popularList = arrayListOf<DisplayItem>()

        when (item) {
            is DataFetchResult.Success -> {
                item.data.results.let { _resultsList ->
                    popularList.add(CategoryTitle(categoryTitle = "Popular"))
                    Observable.fromIterable(_resultsList).subscribe { _results ->
                        popularList.add(
                            MovieCardDTO(
                                results = _results
                            )
                        )
                    }.addTo(compositeDisposable)
                }
            }
            else -> {

            }
        }

        return popularList
    }
}

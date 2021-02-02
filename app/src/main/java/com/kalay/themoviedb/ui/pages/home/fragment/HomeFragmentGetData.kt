package com.kalay.themoviedb.ui.pages.home.fragment

import com.kalay.component.ui.slider.SliderDTO
import com.kalay.component.ui.slider.SliderListDTO
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
    fun getSliderData(item: DataFetchResult<HomePageResponse>?): DisplayItem? {
        val sliderList = arrayListOf<SliderDTO>()
        var sliderListDTO: SliderListDTO? = null

        when (item) {
            is DataFetchResult.Success -> {
                item.data.results.let {_resultsList->
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
            sliderListDTO = SliderListDTO(sliderList = sliderList)
        }
        return sliderListDTO
    }

}

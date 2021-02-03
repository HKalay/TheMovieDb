package com.kalay.themoviedb.ui.pages.search

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.extensions.addTo
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.dataclasses.Results
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class SearchFragmentGetData(
    private val compositeDisposable: CompositeDisposable
) {
    // SEARCH DATA
    fun getSearchData(items: List<Results>?): MutableList<DisplayItem> {
        val searchList = mutableListOf<DisplayItem>()
        items?.let {
            Observable.fromIterable(items).subscribe { _results ->
                searchList.add(
                    MovieCardDTO(
                        results = _results
                    )
                )

            }.addTo(compositeDisposable)
        }
        return searchList
    }
}

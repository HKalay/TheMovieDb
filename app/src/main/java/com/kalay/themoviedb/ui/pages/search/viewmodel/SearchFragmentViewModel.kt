package com.kalay.themoviedb.ui.pages.search.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.extensions.toLiveEvent
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.SearchPageResponse
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

    val searchPageDataResult: LiveData<DataFetchResult<*>> =
        Transformations.map(repository.searchMovieDataResult.toLiveEvent(disposables)) { result ->
            when (result) {
                is DataFetchResult.Progress -> {
                    result
                }

                is DataFetchResult.Failure -> {
                    result
                }

                is DataFetchResult.Success -> {
                    setSearchPageList(result)
                }
            }
        }

    private fun setSearchPageList(
        searchPageResponse: DataFetchResult<SearchPageResponse>
    ): DataFetchResult<*> {

        searchItemList.clear()
        when (searchPageResponse) {
            is DataFetchResult.Success -> {
                searchFragmentGetData.getSearchData(searchPageResponse.data.results)
                    .let {
                        searchItemList.addAll(it)
                    }
            }
            else -> {

            }
        }

        // Movie Card Saved Status
        movieCardStatus()

        return DataFetchResult.success(searchItemList)
    }

    fun getSearchData(searchQuery: String) {
        repository.getSearchMovieData(searchQuery)
    }

    private fun movieCardStatus() {
        val list = repository.getAllLocalMovieList
        searchItemList.map {
            when (it) {
                is MovieCardDTO -> {
                    val isSaved = list.any { localMovieCardDTO ->
                        it.results?.id == localMovieCardDTO.results?.id
                    }
                    it.movieCardIsSaved = isSaved
                }
            }
        }
    }

}

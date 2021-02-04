package com.kalay.themoviedb.ui.pages.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kalay.core.extensions.toLiveEvent
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.response.MovieDetailResponse
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.moviedetail.MovieDetailFragmentGetData
import com.kalay.themoviedb.ui.pages.moviedetail.repository.MovieDetailFragmentRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class MovieDetailFragmentViewModel @Inject constructor(
    val repository: MovieDetailFragmentRepository
) : BaseFragmentViewModel() {

    private val movieDetailItemList = mutableListOf<DisplayItem>()
    private val compositeDisposable = CompositeDisposable()
    private val movieDetailFragmentGetData =
        MovieDetailFragmentGetData(
            compositeDisposable
        )

    val movieDetailPageDataResult: LiveData<DataFetchResult<*>> =
        Transformations.map(repository.movieDetailPageDataResult.toLiveEvent(disposables)) { result ->
            when (result) {
                is DataFetchResult.Progress -> {
                    result
                }

                is DataFetchResult.Failure -> {
                    result
                }

                is DataFetchResult.Success -> {
                    setMovieDetailPageList(result)
                }
            }
        }

    private fun setMovieDetailPageList(
        searchPageResponse: DataFetchResult<MovieDetailResponse>
    ): DataFetchResult<*> {

        movieDetailItemList.clear()
        when (searchPageResponse) {
            is DataFetchResult.Success -> {
                movieDetailFragmentGetData.getMovieData(searchPageResponse.data)
                    .let {
                        movieDetailItemList.addAll(it)
                    }
            }
            else -> {

            }
        }

        return DataFetchResult.success(movieDetailItemList)
    }

    fun getMovieDetailData(movieId: Int) {
        repository.getMovieDetailPageData(movieId)
    }


}

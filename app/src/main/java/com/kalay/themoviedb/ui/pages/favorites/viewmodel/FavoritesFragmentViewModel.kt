package com.kalay.themoviedb.ui.pages.favorites.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.extensions.addTo
import com.kalay.core.extensions.toLiveData
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.data.database.model.MovieCardDbDTO
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import com.kalay.themoviedb.ui.pages.favorites.repository.FavoritesFragmentRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@FragmentScope
class FavoritesFragmentViewModel @Inject constructor(
    val repository: FavoritesFragmentRepository,
    val sharedPreferences: SharedPreferences
) : BaseFragmentViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val savedFavoriteDataStatusResult: LiveData<DataFetchResult<*>> =
        Transformations.map(repository.getLocalAllMovieDataResult.toLiveData(disposables)) { result ->
            when (result) {
                is DataFetchResult.Progress -> {
                    result
                }
                is DataFetchResult.Failure -> {
                    result
                }
                is DataFetchResult.Success -> {
                    getFavoriteListData(result)
                }
            }
        }


    private fun getFavoriteListData(data: DataFetchResult.Success<List<MovieCardDbDTO>>): DataFetchResult<*> {
        val favoriteList = mutableListOf<DisplayItem>()
        data.data.let {
            Observable.fromIterable(it).subscribe {_movieCardDbDto->
                favoriteList.add(MovieCardDTO(_movieCardDbDto.results,true))
            }.addTo(compositeDisposable)
        }
        return DataFetchResult.success(favoriteList)
    }
}

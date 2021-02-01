package com.kalay.themoviedb.ui.pages.favorites.repository

import com.kalay.core.networking.DataFetchResult
import io.reactivex.subjects.BehaviorSubject

interface FavoritesFragmentContract {

    interface Repository {

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {

    }
}

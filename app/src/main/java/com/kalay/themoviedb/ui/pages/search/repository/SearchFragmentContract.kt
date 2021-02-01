package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.core.networking.DataFetchResult
import io.reactivex.subjects.BehaviorSubject

interface SearchFragmentContract {

    interface Repository {

        fun <T> handleError(result: BehaviorSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {

    }
}

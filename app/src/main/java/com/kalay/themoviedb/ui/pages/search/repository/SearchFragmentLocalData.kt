package com.kalay.themoviedb.ui.pages.search.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@FragmentScope
class SearchFragmentLocalData(
    private val movieCardDBDao: MovieCardDBDao
) : SearchFragmentContract.Local {

    override fun insertLocalMovie(movieCardDTO: MovieCardDTO) {
        GlobalScope.launch {
            movieCardDBDao.insertMovie(MovieCardDbDTO(movieCardDTO.results?.id,movieCardDTO.results))
        }
    }

    override fun deleteLocalMovie(movieCardDTO: MovieCardDTO) {
        GlobalScope.launch {
            movieCardDBDao.deleteMovie(MovieCardDbDTO(movieCardDTO.results?.id,movieCardDTO.results))
        }
    }

    override fun getAllLocalMovie(): Single<List<MovieCardDbDTO>> {
        return movieCardDBDao.getAllMovie()
    }
}
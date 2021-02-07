package com.kalay.themoviedb.ui.pages.favorites.repository

import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.data.database.dao.MovieCardDBDao
import com.kalay.data.database.model.MovieCardDbDTO
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@FragmentScope
class FavoritesFragmentLocalData(
    private val movieCardDBDao: MovieCardDBDao
) : FavoritesFragmentContract.Local {


    override fun getLocalAllMovie(): Single<List<MovieCardDbDTO>> {
        return movieCardDBDao.getAllMovie()
    }

    override fun deleteLocalMovie(movieCardDTO: MovieCardDTO) {
        GlobalScope.launch {
            movieCardDBDao.deleteMovie(MovieCardDbDTO(movieCardDTO.results?.id,movieCardDTO.results))
        }
    }

}
package com.kalay.themoviedb.ui.databasehelper

import android.app.Activity
import android.content.Context
import com.afollestad.rxkprefs.Pref
import com.afollestad.rxkprefs.RxkPrefs
import com.afollestad.rxkprefs.rxkPrefs
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.enums.SharedPref
import com.kalay.themoviedb.ui.pages.favorites.repository.FavoritesFragmentRepository
import com.kalay.themoviedb.ui.pages.home.fragment.repository.HomeFragmentRepository
import com.kalay.themoviedb.ui.pages.moviedetail.repository.MovieDetailFragmentRepository
import com.kalay.themoviedb.ui.pages.search.repository.SearchFragmentRepository
import io.reactivex.disposables.CompositeDisposable

class SaveLocalDbHelper(
    private var repositoryHomeFragment: HomeFragmentRepository? = null,
    private var repositoryMovieDetailFragment: MovieDetailFragmentRepository? = null,
    private var repositorySearchFragment: SearchFragmentRepository? = null,
    private var repositoryFavoritesFragment: FavoritesFragmentRepository? = null,
    val requireContext: Context,
    val compositeDisposable: CompositeDisposable,
    private val pageType: String,
    val activity: Activity
) {

    private lateinit var myPrefs: RxkPrefs
    private var mySaveMovieStatus: Pref<Boolean>? = null


    fun getAllLocalMovieCardData() {
        when (pageType) {
            PageType.HomeFragment.toString() -> {
                repositoryHomeFragment?.getAllLocalMovie()
            }
            PageType.SearchFragment.toString() -> {
                repositorySearchFragment?.getAllLocalMovie()
            }
            PageType.FavoritesFragment.toString() -> {
                repositoryFavoritesFragment?.getLocalAllMovie()
            }
        }
    }

    fun getMovieCardDataWithId(movieId: Int?) {
        when (pageType) {
            PageType.MovieDetailFragment.toString() -> {
                repositoryMovieDetailFragment?.getAllLocalMovieWithId(
                    movieId
                )
            }
        }
    }

    fun deleteLocalMovieCard(movieCardDTO: MovieCardDTO) {
        when (pageType) {
            PageType.HomeFragment.toString() -> {
                repositoryHomeFragment?.deleteLocalMovie(movieCardDTO)
            }
            PageType.MovieDetailFragment.toString() -> {
                repositoryMovieDetailFragment?.deleteLocalMovie(movieCardDTO)
            }
            PageType.SearchFragment.toString() -> {
                repositorySearchFragment?.deleteLocalMovie(movieCardDTO)
            }
            PageType.FavoritesFragment.toString() -> {
                repositoryFavoritesFragment?.deleteLocalMovie(movieCardDTO)
            }
        }

        myPrefs = rxkPrefs(requireContext, SharedPref.PREF_KEY.value)
        mySaveMovieStatus = myPrefs.boolean(movieCardDTO.results?.id.toString(), false)
        mySaveMovieStatus!!.set(false)
    }

    fun insertMovieCardData(movieCardDTO: MovieCardDTO) {
        when (pageType) {
            PageType.HomeFragment.toString() -> {
                repositoryHomeFragment?.insertLocalMovie(movieCardDTO)
            }
            PageType.MovieDetailFragment.toString() -> {
                repositoryMovieDetailFragment?.insertLocalMovie(movieCardDTO)
            }
            PageType.SearchFragment.toString() -> {
                repositorySearchFragment?.insertLocalMovie(movieCardDTO)
            }
        }

        myPrefs = rxkPrefs(requireContext, SharedPref.PREF_KEY.value)
        mySaveMovieStatus = myPrefs.boolean(movieCardDTO.results?.id.toString(), true)
        mySaveMovieStatus!!.set(true)
    }
}
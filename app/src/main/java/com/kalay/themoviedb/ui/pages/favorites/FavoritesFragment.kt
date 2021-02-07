package com.kalay.themoviedb.ui.pages.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.favorites.viewmodel.FavoritesFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.Observer
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visible
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.ui.common.navigation.NavigationManager
import com.kalay.themoviedb.ui.databasehelper.SaveLocalDbHelper
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : BaseDataFetchFragment<FavoritesFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable

    lateinit var favoritesList: MutableList<MovieCardDTO>

    @Inject
    lateinit var adapterPageList: TheMovieRecyclerviewAdapter

    private var saveLocalDbHelper: SaveLocalDbHelper? = null

    override val viewModelClass = FavoritesFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_favorites

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()
        saveLocalDbHelper = SaveLocalDbHelper(
            repositoryFavoritesFragment = viewModel.repository,
            requireContext = requireContext(),
            compositeDisposable = compositeDisposable,
            pageType = PageType.FavoritesFragment.toString(),
            activity = requireActivity()
        )
        bindFavoritesList()
        adapterPageListClick()
    }

    private fun bindFavoritesList() {
        saveLocalDbHelper?.getAllLocalMovieCardData()
        rvFavoritesPage.setup(
            adapter = adapterPageList.getAdapter()
        )

        viewModel.savedFavoriteDataStatusResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    pbFavoritesPage.gone()
                    llContent.visible()
                    rvFavoritesPage.gone()
                }
                is DataFetchResult.Progress -> {
                    pbFavoritesPage.visible()
                }
                is DataFetchResult.Success -> {
                    it.data?.let {
                        val data = it as MutableList<MovieCardDTO>
                        favoritesList = data
                        favoriteListNullControl()
                        pbFavoritesPage.gone()
                    }
                }
            }
        })
    }

    private fun adapterPageListClick() {
        adapterPageList.getAdapter().itemViewClickListener =
            { view: View, item: DisplayItem, position: Int ->
                when (item) {
                    is MovieCardDTO -> {
                        if (view.id == com.kalay.component.R.id.rootViewItemMovieCard) {
                            NavigationManager().navigate(model = item, context = requireContext())
                        }
                        if (view.id == com.kalay.component.R.id.imgItemMovieCardSave) {
                            movieCardClick(item,position)
                        }
                    }
                }
            }
    }

    private fun movieCardClick(movieCardDTO: MovieCardDTO,position:Int) {
        favoritesList.removeAt(position)
        if (movieCardDTO.movieCardIsSaved) {
            saveLocalDbHelper?.deleteLocalMovieCard(movieCardDTO)
            favoriteListNullControl()
        }
    }

    private fun favoriteListNullControl(){
        adapterPageList.getAdapter().updateAllItems(favoritesList)
        if (favoritesList.isNullOrEmpty()) {
            llContent.visible()
            rvFavoritesPage.gone()
        } else {
            llContent.gone()
            rvFavoritesPage.visible()
            adapterPageList.getAdapter().updateAllItems(favoritesList)
        }
    }
}


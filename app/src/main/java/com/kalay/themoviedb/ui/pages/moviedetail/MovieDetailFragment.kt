package com.kalay.themoviedb.ui.pages.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.core.enums.ParcelableData
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visible
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.R
import androidx.lifecycle.Observer
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.extensions.loadImage
import com.kalay.data.response.dataclasses.Results
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.databasehelper.SaveLocalDbHelper
import com.kalay.themoviedb.ui.pages.moviedetail.viewmodel.MovieDetailFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : BaseDataFetchFragment<MovieDetailFragmentViewModel>() {

    private var results: Results? = null
    private var saveLocalDbHelper: SaveLocalDbHelper? = null

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) = MovieDetailFragment().apply {
            bundle?.let {
                results = bundle.getParcelable(ParcelableData.MOVIE.toString()) as Results
            }
        }
    }

    lateinit var compositeDisposable: CompositeDisposable

    private val moviePageList = mutableListOf<DisplayItem>()

    @Inject
    lateinit var adapterMovieDetailPageList: TheMovieRecyclerviewAdapter

    override val viewModelClass = MovieDetailFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_movie_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()
        saveLocalDbHelper = SaveLocalDbHelper(
            repositoryMovieDetailFragment = viewModel.repository,
            requireContext = requireContext(),
            compositeDisposable = compositeDisposable,
            pageType = PageType.MovieDetailFragment.toString(),
            activity = requireActivity()
        )

        rvMovieDetail.setup(
            adapter = adapterMovieDetailPageList.getAdapter()
        )

        bindMoviePage(results?.id)
        buttonClick()
    }

    private fun bindMoviePage(movieId: Int?) {

        viewModel.getMovieDetailData(movieId)

        saveLocalDbHelper?.getMovieCardDataWithId(results?.id)

        viewModel.getLocalMovieDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    imgSaveDelete.loadImage(R.drawable.ic_save)
                }
                is DataFetchResult.Progress -> {

                }
                is DataFetchResult.Success -> {
                    imgSaveDelete.loadImage(R.drawable.ic_saved)
                }
            }
        })

        viewModel.movieDetailPageDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    pbMovieDetailPage?.gone()

                }
                is DataFetchResult.Progress -> {
                    pbMovieDetailPage?.visible()
                }
                is DataFetchResult.Success -> {
                    moviePageList.clear()
                    moviePageList.addAll(it.data as MutableList<DisplayItem>)
                    adapterMovieDetailPageList.getAdapter()
                        .updateAllItems(moviePageList)
                    pbMovieDetailPage?.gone()
                }
            }
        })
    }

    private fun buttonClick() {
        imgBack.setOnClickListener { requireActivity().onBackPressed() }

        imgSaveDelete.setOnClickListener {
            movieCardClick(MovieCardDTO(results = results))
        }
    }

    private fun movieCardClick(movieCardDTO: MovieCardDTO) {

        saveLocalDbHelper?.getMovieCardDataWithId(movieCardDTO.results?.id)

        viewModel.getLocalMovieDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    imgSaveDelete.loadImage(R.drawable.ic_saved)
                    saveLocalDbHelper?.insertMovieCardData(movieCardDTO)
                }
                is DataFetchResult.Progress -> {

                }
                is DataFetchResult.Success -> {
                    imgSaveDelete.loadImage(R.drawable.ic_save)
                    saveLocalDbHelper?.deleteLocalMovieCard(movieCardDTO)
                }
            }
        })
    }
}

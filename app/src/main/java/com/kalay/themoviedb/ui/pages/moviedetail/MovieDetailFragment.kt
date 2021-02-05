package com.kalay.themoviedb.ui.pages.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.core.enums.ParcelableData
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visibile
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.R
import androidx.lifecycle.Observer
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.moviedetail.viewmodel.MovieDetailFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : BaseDataFetchFragment<MovieDetailFragmentViewModel>() {

    private var movieId: Int = 0


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?) = MovieDetailFragment().apply {
            bundle?.let {
                movieId = bundle.getInt(ParcelableData.MOVIE_ID.toString(), 0)
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

        rvMovieDetail.setup(
            adapter = adapterMovieDetailPageList.getAdapter()
        )

        bindMoviePage(movieId)
    }

    private fun bindMoviePage(movieId: Int) {

        viewModel.getMovieDetailData(movieId)

        viewModel.movieDetailPageDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    pbMovieDetailPage?.gone()

                }
                is DataFetchResult.Progress -> {
                    pbMovieDetailPage?.visibile()
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
}

package com.kalay.themoviedb.ui.pages.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.component.ui.moviecard.MovieCardDTO
import com.kalay.core.enums.PageType
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visible
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.common.navigation.NavigationManager
import com.kalay.themoviedb.ui.databasehelper.SaveLocalDbHelper
import com.kalay.themoviedb.ui.pages.search.viewmodel.SearchFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseDataFetchFragment<SearchFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var adapterPageList: TheMovieRecyclerviewAdapter

    private val searchPageList = mutableListOf<DisplayItem>()

    private var saveLocalDbHelper: SaveLocalDbHelper? = null

    override val viewModelClass = SearchFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_search

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveLocalDbHelper?.getAllLocalMovieCardData()

        compositeDisposable = CompositeDisposable()
        saveLocalDbHelper = SaveLocalDbHelper(
            repositorySearchFragment = viewModel.repository,
            requireContext = requireContext(),
            compositeDisposable = compositeDisposable,
            pageType = PageType.SearchFragment.toString(),
            activity = requireActivity()
        )

        rvSearchPage.setup(
            adapter = adapterPageList.getAdapter()
        )

        etSearchView.addTextChangedListener(searchWatcher)
        bindSearchPage("")
        adapterPageListClick()

    }


    private fun bindSearchPage(searchText: String) {

        viewModel.getSearchData(searchText)

        viewModel.searchPageDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    pbSearchPage?.gone()
                    rvSearchPage.gone()
                    llNoResult.visible()
                }
                is DataFetchResult.Progress -> {
                    pbSearchPage?.visible()
                }
                is DataFetchResult.Success -> {
                    searchPageList.clear()
                    searchPageList.addAll(it.data as MutableList<DisplayItem>)
                    adapterPageList.getAdapter()
                        .updateAllItems(searchPageList)
                    llNoResult.gone()
                    pbSearchPage?.gone()
                    rvSearchPage.visible()
                }
            }
        })
    }

    private fun adapterPageListClick() {

        adapterPageList.getAdapter().itemViewClickListener =
            { view: View, item: DisplayItem, _: Int ->
                when (item) {
                    is MovieCardDTO -> {
                        if (view.id == com.kalay.component.R.id.rootViewItemMovieCard) {
                            NavigationManager().navigate(model = item, context = requireContext())
                        }
                        if (view.id == com.kalay.component.R.id.imgItemMovieCardSave) {
                            movieCardClick(item)
                        }
                    }
                }
            }
    }

    private val searchWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (!etSearchView.text.isNullOrEmpty()) {
                bindSearchPage(etSearchView.text.toString())
            } else {
                bindSearchPage("")
            }
        }
    }

    private fun movieCardClick(movieCardDTO: MovieCardDTO) {
        if (movieCardDTO.movieCardIsSaved) {
            saveLocalDbHelper?.deleteLocalMovieCard(movieCardDTO)
        } else {
            saveLocalDbHelper?.insertMovieCardData(movieCardDTO)
        }
    }
}
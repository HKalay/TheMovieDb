package com.kalay.themoviedb.ui.pages.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.kalay.component.TheMovieRecyclerviewAdapter
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.setup
import com.kalay.core.extensions.visibile
import com.kalay.core.networking.DataFetchResult
import com.kalay.core.ui.recyclerview.DisplayItem
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.fragment.BaseDataFetchFragment
import com.kalay.themoviedb.ui.pages.search.viewmodel.SearchFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseDataFetchFragment<SearchFragmentViewModel>() {

    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var adapterPageList: TheMovieRecyclerviewAdapter

    private val searchPageList = mutableListOf<DisplayItem>()

    override val viewModelClass = SearchFragmentViewModel::class.java

    override val layoutViewRes = R.layout.fragment_search

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

        rvSearchPage.setup(
            adapter = adapterPageList.getAdapter()
        )

        etSearchView.addTextChangedListener(searchWatcher)
        bindSearchPage("")

    }


    private fun bindSearchPage(searchText: String) {

        viewModel.getSearchData(searchText)

        viewModel.searchPageDataResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataFetchResult.Failure -> {
                    pbSearchPage?.gone()
                    rvSearchPage.gone()
                    llNoResult.visibile()
                }
                is DataFetchResult.Progress -> {
                    pbSearchPage?.visibile()
                }
                is DataFetchResult.Success -> {
                    searchPageList.clear()
                    searchPageList.addAll(it.data as MutableList<DisplayItem>)
                    adapterPageList.getAdapter()
                        .updateAllItems(searchPageList)
                    llNoResult.gone()
                    pbSearchPage?.gone()
                    rvSearchPage.visibile()
                }
            }
        })
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
}
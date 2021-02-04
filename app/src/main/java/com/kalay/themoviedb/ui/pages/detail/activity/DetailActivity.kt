package com.kalay.themoviedb.ui.pages.detail.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kalay.core.enums.PageType
import com.kalay.core.enums.ParcelableData
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.activity.BaseActivity
import com.kalay.themoviedb.ui.pages.detail.activity.viewmodel.DetailActivityViewModel
import com.kalay.themoviedb.ui.pages.detail.fragment.MovieDetailMasterFragment
import com.kalay.themoviedb.ui.pages.moviedetail.MovieDetailFragment

class DetailActivity : BaseActivity<DetailActivityViewModel>() {

    override val layoutViewRes = R.layout.activity_detail
    override val viewModelClass = DetailActivityViewModel::class.java
    var pageType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pageType =
            intent.getStringExtra(ParcelableData.PAGE_TYPE.toString())?.toString()

        when (pageType) {
            PageType.Slider.toString() -> {
                navigateToFragment(
                    MovieDetailMasterFragment.newInstance(
                        intent.extras
                    )
                )
            }
            else -> {
                navigateToFragment(MovieDetailFragment.newInstance(intent.extras))
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_detail_main_container, fragment)
        transaction.commit()
    }
}




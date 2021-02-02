package com.kalay.themoviedb.ui.pages.home.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kalay.core.extensions.gone
import com.kalay.core.extensions.visibile
import com.kalay.themoviedb.R
import com.kalay.themoviedb.ui.base.activity.BaseActivity
import com.kalay.themoviedb.ui.pages.favorites.FavoritesFragment
import com.kalay.themoviedb.ui.pages.home.activity.viewmodel.HomeActivityViewModel
import com.kalay.themoviedb.ui.pages.home.fragment.HomeFragment
import com.kalay.themoviedb.ui.pages.search.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeActivityViewModel>() {

    var bottomNavigationPosition = R.id.homepage

    override val viewModelClass = HomeActivityViewModel::class.java

    override val layoutViewRes = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        selectTab(bottomNavigationPosition)
    }

    override fun onBackPressed() {
        if (bottomNavigationPosition != R.id.homepage) {
            bottomNavigationView?.menu?.findItem(R.id.homepage)?.isChecked = true
            selectTab(R.id.homepage)
        } else {
            finish()
        }
    }

    private fun navigateToFragment(fragment: Fragment, containerId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment, containerId.toString())
        transaction.commit()
    }

    private fun invisibleTabContainerExcept(container: View) {
        homeTabContainer.gone()
        searchTabContainer.gone()
        favoriteTabContainer.gone()
        container.visibile()
    }

    private fun selectTab(itemId: Int) {
        bottomNavigationPosition = itemId

        when (itemId) {
            R.id.homepage -> {
                val hf: HomeFragment? =
                    supportFragmentManager.findFragmentByTag(R.id.homeTabContainer.toString()) as HomeFragment?
                if (hf == null) {
                    navigateToFragment(
                        HomeFragment(),
                        R.id.homeTabContainer
                    )
                }
                invisibleTabContainerExcept(homeTabContainer)
            }
            R.id.search -> {
                val sf: SearchFragment? =
                    supportFragmentManager.findFragmentByTag(R.id.searchTabContainer.toString()) as SearchFragment?
                if (sf == null) {
                    navigateToFragment(
                        SearchFragment(),
                        R.id.searchTabContainer
                    )
                }
                invisibleTabContainerExcept(searchTabContainer)
            }
            R.id.favorites -> {
                val ff: FavoritesFragment? =
                    supportFragmentManager.findFragmentByTag(R.id.favoriteTabContainer.toString()) as FavoritesFragment?
                if (ff == null) {
                    navigateToFragment(
                        FavoritesFragment(),
                        R.id.favoriteTabContainer
                    )
                }
                invisibleTabContainerExcept(favoriteTabContainer)
            }
        }
    }

    private fun clickBottomNavigation() {
        bottomNavigationView?.setOnNavigationItemSelectedListener {
            bottomNavigationPosition = it.itemId
            selectTab(bottomNavigationPosition)
            return@setOnNavigationItemSelectedListener true
        }
    }
}
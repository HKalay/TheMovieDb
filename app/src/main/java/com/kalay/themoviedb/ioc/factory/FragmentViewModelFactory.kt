@file:Suppress("UNCHECKED_CAST")

package com.kalay.themoviedb.ioc.factory

import androidx.lifecycle.ViewModel
import com.kalay.core.ioc.scopes.FragmentScope
import com.kalay.themoviedb.ui.base.viewmodel.BaseFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider


@FragmentScope
class FragmentViewModelFactory @Inject constructor(
    creators: Map<Class<out BaseFragmentViewModel>,
            @JvmSuppressWildcards Provider<BaseFragmentViewModel>>
) : BaseViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)
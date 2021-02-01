@file:Suppress("UNCHECKED_CAST")

package com.kalay.themoviedb.ioc.factory

import androidx.lifecycle.ViewModel
import com.kalay.themoviedb.ui.base.viewmodel.BaseActivityViewModel
import com.kalay.core.ioc.scopes.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ActivityViewModelFactory @Inject constructor(
    creators: Map<Class<out BaseActivityViewModel>
            , @JvmSuppressWildcards Provider<BaseActivityViewModel>>
) : BaseViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)
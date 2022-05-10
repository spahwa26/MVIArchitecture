package com.nickelfox.myfinaltest.ui.categories

import com.nickelfox.myfinaltest.data.models.CategoriesModel
import com.simple.mvi.common.ViewState

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
sealed class CategoriesState : ViewState {
    object Loading : CategoriesState()
    data class ResultAllPersona(val data: List<CategoriesModel>) : CategoriesState()
    data class ResultSearch(val data: List<CategoriesModel>) : CategoriesState()
    data class Exception(val callErrors: CategoriesModel) : CategoriesState()
}
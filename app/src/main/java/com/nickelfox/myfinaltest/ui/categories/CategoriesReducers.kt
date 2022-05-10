package com.nickelfox.myfinaltest.ui.categories

import com.nickelfox.myfinaltest.data.models.CategoriesModel
import com.nickelfox.myfinaltest.data.models.CustomResult

/**
 * Created by Rim Gazzah on 8/31/20.
 **/

fun CustomResult<List<CategoriesModel>>.reduce(isSearchMode: Boolean = false): CategoriesState {
    return when (this) {
        is CustomResult.Success -> if (isSearchMode) CategoriesState.ResultSearch(data) else CategoriesState.ResultAllPersona(
            data
        )
        is CustomResult.Error -> CategoriesState.Exception(exception)
        is CustomResult.Loading -> CategoriesState.Loading
    }
}
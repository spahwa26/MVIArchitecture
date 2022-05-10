package com.nickelfox.myfinaltest.ui.categories

import com.simple.mvi.common.ViewAction

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
sealed class CategoriesAction : ViewAction {
    data class SearchCharacters(val name: String) : CategoriesAction()
    object AllCharacters : CategoriesAction()
}
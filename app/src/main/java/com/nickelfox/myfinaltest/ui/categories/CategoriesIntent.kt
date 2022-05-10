package com.nickelfox.myfinaltest.ui.categories

import com.simple.mvi.common.ViewIntent

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
sealed class CategoriesIntent : ViewIntent {
    object LoadAllCharacters : CategoriesIntent()
    data class SearchCharacter(val name: String) : CategoriesIntent()
    object ClearSearch : CategoriesIntent()
}
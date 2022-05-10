package com.nickelfox.myfinaltest.utils

import androidx.lifecycle.LiveData

/**
 * Created by Rim Gazzah on 8/20/20.
 **/

interface IModel<STATE, INTENT> {

    val state: LiveData<STATE>

    fun dispatchIntent(intent: INTENT)
}
package com.nickelfox.myfinaltest.utils.baseclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickelfox.myfinaltest.utils.IModel
import com.simple.mvi.common.ViewAction
import com.simple.mvi.common.ViewIntent
import com.simple.mvi.common.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Rim Gazzah on 8/20/20.
 **/
abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState> :
    ViewModel(),
    IModel<STATE, INTENT> {

    protected val mState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return mState
        }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    final override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)
}
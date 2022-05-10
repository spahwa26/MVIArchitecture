package com.nickelfox.myfinaltest.utils.baseclasses

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nickelfox.myfinaltest.utils.IViewRenderer
import com.simple.mvi.common.ViewAction
import com.simple.mvi.common.ViewIntent
import com.simple.mvi.common.ViewState

/**
 * Created by Rim Gazzah on 8/19/20.
 **/
abstract class BaseFragment<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>>() :
    Fragment(), IViewRenderer<STATE> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()
    abstract fun initData()
}
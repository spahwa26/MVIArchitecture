package com.nickelfox.myfinaltest.data.models

import com.nickelfox.myfinaltest.utils.LocalisedException


sealed class CustomResult<out R> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Error(
        val exception: LocalisedException,
    ) : CustomResult<Nothing>()
}

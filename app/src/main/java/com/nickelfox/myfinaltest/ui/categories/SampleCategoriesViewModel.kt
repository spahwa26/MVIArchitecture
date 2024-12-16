package com.nickelfox.myfinaltest.ui.categories

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nickelfox.myfinaltest.data.models.CategoriesModel
import com.nickelfox.myfinaltest.data.models.CustomResult
import com.nickelfox.myfinaltest.data.respository.DefaultRepo
import com.nickelfox.myfinaltest.utils.Constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleCategoriesViewModel @Inject constructor(private val repo: DefaultRepo) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableLiveData<UIState>()
    val state: LiveData<UIState> = _state

    init {
        handleIntent()
        getCategories()
    }

    fun getCategories() =
        repo.getCategoriesFromDb().combine(
            repo.getCategoryImagesFromDb()
        ) { categories, images ->
            categories.map { category ->
                val url = images.find { it.imageId == category.categoryId }?.imageUrl.orEmpty()
                CategoriesModel(url, category.categoryDisplayName, category.isActive)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchCategories -> updateCategories()
                }
            }
        }
    }

    private fun updateCategories() {
        viewModelScope.launch {
            _state.value = UIState.Loading(View.VISIBLE)
            delay(2000)
            when (val result = repo.getCategories()) {
                is CustomResult.Success -> {
                    _state.value = UIState.Loading(View.GONE)
                }

                is CustomResult.Error -> {
                    _state.value = UIState.Loading(View.GONE)
                    _state.postValue(UIState.Error(result.exception.message ?: EMPTY_STRING))
                }
            }
        }
    }

    sealed class UIState {
        data class Loading(val visibility: Int = View.GONE) : UIState()
        data class Error(val message: String) : UIState()
    }


}

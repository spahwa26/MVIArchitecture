package com.nickelfox.myfinaltest.data.respository

import com.nickelfox.myfinaltest.data.db.GenieDatabase
import com.nickelfox.myfinaltest.data.models.CustomResult
import com.nickelfox.myfinaltest.utils.Constants.SUCCESS
import javax.inject.Inject

class DefaultRepo @Inject constructor(
    private val remoteRepo: RemoteRepository,
    private val database: GenieDatabase
) {

    fun getCategoriesFromDb() = database.categoryDao().getCategories()

    fun getCategoryImagesFromDb() = database.categoryDao().getCategoryImages()


    suspend fun getCategories(): CustomResult<String> {
        return when (val result = remoteRepo.getCategories()) {
            is CustomResult.Success -> {
                database.categoryDao().insertUpdateCategories(result.data.first)
                database.categoryDao().insertUpdateCategoryImages(result.data.second)
                CustomResult.Success(SUCCESS)
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}

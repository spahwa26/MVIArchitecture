package com.nickelfox.myfinaltest.data.respository

import com.nickelfox.myfinaltest.data.db.entities.Categories
import com.nickelfox.myfinaltest.data.db.entities.CategoryImages
import com.nickelfox.myfinaltest.data.models.CustomResult
import com.nickelfox.myfinaltest.data.network.Api
import com.nickelfox.myfinaltest.data.network.SafeApiRequest
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val client: Api
) : SafeApiRequest() {
    suspend fun getCategories(): CustomResult<Pair<List<Categories>, List<CategoryImages>>> {
        return when (val result = apiRequest { client.getCategories() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImages() }) {
                    is CustomResult.Success -> {
                        CustomResult.Success(Pair(result.data.data, imageResult.data.data))
                    }
                    is CustomResult.Error -> {
                        CustomResult.Error(imageResult.exception)
                    }
                }
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}

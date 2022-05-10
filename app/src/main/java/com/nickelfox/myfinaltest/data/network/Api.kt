package com.nickelfox.myfinaltest.data.network

import com.nickelfox.myfinaltest.data.db.entities.Categories
import com.nickelfox.myfinaltest.data.db.entities.CategoryImages
import com.nickelfox.myfinaltest.data.models.CommonResponse
import retrofit2.Response
import retrofit2.http.GET


//TODO: these are just sample calls for demonstration, replace them with your own
interface Api {

    @GET("categories")
    suspend fun getCategories(): Response<CommonResponse<List<Categories>>>

    @GET("category_images")
    suspend fun getCategoryImages(): Response<CommonResponse<List<CategoryImages>>>

}

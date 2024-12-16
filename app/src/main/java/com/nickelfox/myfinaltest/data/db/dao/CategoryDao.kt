package com.nickelfox.myfinaltest.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nickelfox.myfinaltest.data.db.entities.Categories
import com.nickelfox.myfinaltest.data.db.entities.CategoryImages
import com.nickelfox.myfinaltest.data.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdateCategories(list: List<Categories>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdateCategoryImages(list: List<CategoryImages>)

//    @Query("SELECT Category.category_display_name, Category.isactive, CategoryImages.imageUrl FROM Category INNER JOIN CategoryImages ON Category.category_id = CategoryImages.imageId")
//    fun getCategoriesWithImages(): Flow<List<CategoriesModel>>

    @Query("Select * from Category")
    fun getCategories(): Flow<List<Categories>>

    @Query("Select * from CategoryImages")
    fun getCategoryImages(): Flow<List<CategoryImages>>

}

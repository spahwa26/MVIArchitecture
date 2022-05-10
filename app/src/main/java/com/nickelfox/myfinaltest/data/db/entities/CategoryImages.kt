package com.nickelfox.myfinaltest.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryImages")
data class CategoryImages(
    @PrimaryKey @ColumnInfo(name = "imageId") val imageId: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
)

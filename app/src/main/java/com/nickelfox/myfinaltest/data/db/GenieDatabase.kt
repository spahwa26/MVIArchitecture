package com.nickelfox.myfinaltest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nickelfox.myfinaltest.data.db.dao.CategoryDao
import com.nickelfox.myfinaltest.data.db.dao.EmployeesDao
import com.nickelfox.myfinaltest.data.db.entities.Categories
import com.nickelfox.myfinaltest.data.db.entities.CategoryImages
import com.nickelfox.myfinaltest.data.db.entities.Employees

@Database(entities = [Categories::class, CategoryImages::class, Employees::class], version = 1, exportSchema = false)
abstract class GenieDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun employeesDao(): EmployeesDao
}

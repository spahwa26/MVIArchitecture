package com.nickelfox.myfinaltest.data.local

import android.content.Context
import androidx.room.Room
import com.nickelfox.myfinaltest.data.db.GenieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GenieDatabase::class.java, "MyFinalTest.db")
            .fallbackToDestructiveMigration().build()
}

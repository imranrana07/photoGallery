package com.imran.photogallery.di

import android.content.Context
import com.imran.photogallery.data.source.local.database.AppDatabase
import com.imran.photogallery.data.source.local.database.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getAppDatabase(@ApplicationContext context: Context):AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getPhotoDao(appDatabase: AppDatabase):PhotoDao{
        return appDatabase.photoDao()
    }


}
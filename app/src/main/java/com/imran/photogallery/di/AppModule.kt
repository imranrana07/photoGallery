package com.imran.photogallery.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imran.photogallery.data.api.ApiCall
import com.imran.photogallery.data.source.local.database.AppDatabase
import com.imran.photogallery.data.source.local.database.dao.PhotoDao
import com.qcoom.photogallery.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    fun logger(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun client(logger: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun retrofitClient(client: OkHttpClient,gson: Gson):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun apiCall(retrofit: Retrofit):ApiCall{
        return retrofit.create(ApiCall::class.java)
    }

}
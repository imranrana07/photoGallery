package com.imran.photogallery.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.model.PhotosKey
import com.imran.photogallery.data.model.UrlTypeConverter
import com.imran.photogallery.data.model.entities.PhotoEntity
import com.imran.photogallery.data.source.local.database.dao.PhotoDao
import com.imran.photogallery.utils.DATABASE_NAME

@Database(entities = [ Photos::class, PhotosKey::class], version = 1, exportSchema = false)
@TypeConverters(UrlTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

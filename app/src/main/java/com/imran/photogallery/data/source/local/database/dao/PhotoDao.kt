package com.imran.photogallery.data.source.local.database.dao;

import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.imran.photogallery.data.model.entities.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPhotos(entity: PhotoEntity)

    @Query("SELECT * FROM photos ORDER BY id DESC")
    fun getPhotos(): PagingSource<Int,PhotoEntity>

    @Delete
    suspend fun deletePhotos(entity: PhotoEntity)
}

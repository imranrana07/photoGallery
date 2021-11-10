package com.imran.photogallery.data.source.local.database.dao;

import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.model.PhotosKey
import com.imran.photogallery.data.model.entities.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPhotos(photoList: MutableList<Photos>?)

    @Query("SELECT * FROM photos")
    fun getPhotos(): PagingSource<Int,Photos>

    @Query("DELETE FROM photos")
    suspend fun clearPhotos()



    @Insert(onConflict = REPLACE)
    suspend fun addKeys(photoList: List<PhotosKey>)

    @Query("SELECT * FROM photoKey WHERE id = :id")
    fun getPhotoKeys(id: String): PhotosKey

    @Query("DELETE FROM photoKey")
    suspend fun clearPhotoKeys()




}

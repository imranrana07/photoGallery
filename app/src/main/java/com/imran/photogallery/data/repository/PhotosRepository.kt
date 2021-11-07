package com.imran.photogallery.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imran.photogallery.data.api.ApiCall
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.model.entities.PhotoEntity
import com.imran.photogallery.data.source.local.database.dao.PhotoDao
import com.imran.photogallery.data.source.remote.PhotosPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val  apiCall: ApiCall,private val photoDao: PhotoDao) {

    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PhotosPagingSource(apiCall,clientId)
            }
        ).flow
    }

}
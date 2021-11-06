package com.imran.photogallery.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.source.remote.PhotosPagingSource
import kotlinx.coroutines.flow.Flow

class PhotosRepository {
    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PhotosPagingSource(clientId)
            }
        ).flow
    }
}
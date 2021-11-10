package com.imran.photogallery.data.repository

import androidx.paging.*
import com.imran.photogallery.data.api.ApiCall
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.source.local.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val  apiCall: ApiCall,
    private val db: AppDatabase
    ) {

    @ExperimentalPagingApi
    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            remoteMediator = {
                RemoteMediator(apiCall,clientId,db)
            }
        ).flow
    }

}
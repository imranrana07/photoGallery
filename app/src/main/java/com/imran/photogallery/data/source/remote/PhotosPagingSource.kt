package com.imran.photogallery.data.source.remote

import androidx.paging.*
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.imran.photogallery.data.api.ApiCall
import com.imran.photogallery.data.api.ApiResponse
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.model.PhotosKey
import com.imran.photogallery.data.source.local.database.AppDatabase
import com.imran.photogallery.data.source.local.database.dao.PhotoDao
import com.imran.photogallery.utils.ApiException
import com.imran.photogallery.utils.TIME_OUT
import kotlinx.coroutines.withTimeoutOrNull
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PhotosPagingSource @Inject constructor(
    private val apiCall: ApiCall,
    private val clientId:String,
    private val db: AppDatabase
    ):RemoteMediator<Int, Photos>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Photos>): MediatorResult {
        val page =  1
        return try {
            val response = withTimeoutOrNull(TIME_OUT){
                ApiResponse.getResult { apiCall.getPhotos(clientId,page)}
            }
            val endOfPaginationReached = response?.isEmpty()

            db.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    db.photoDao().clearPhotoKeys()
                    db.photoDao().clearPhotos()
                }
                val prevKey = if (page < 1 ) null else page - 1
                val nextKey = page+1 // as no clear pagination idea information there, that is why static as site contains huge page volume
                val keys = response!!.map {
                    PhotosKey(id = it.id, previous = prevKey, next = nextKey)
                }
                db.photoDao().addKeys(keys)
                db.photoDao().addPhotos(response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached!!)
        }catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    /*override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val nextPage = params.key ?: 1
        return try {
            val response = withTimeoutOrNull(TIME_OUT){
                ApiResponse.getResult { apiCall.getPhotos(clientId,nextPage)}
            }
            LoadResult.Page(
                data = response!!,
                prevKey = if(nextPage>1) nextPage-1 else null,
                nextKey = nextPage+1 // as no clear pagination idea information there, that is why static as site contains huge page volume
            )
        }catch (e: ApiException){
            LoadResult.Error(e)
        }
    }*/
}
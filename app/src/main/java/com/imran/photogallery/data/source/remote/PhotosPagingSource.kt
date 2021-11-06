package com.imran.photogallery.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imran.photogallery.data.api.ApiResponse
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.utils.ApiException
import com.imran.photogallery.utils.TIME_OUT
import com.imran.photogallery.utils.apiCall
import kotlinx.coroutines.withTimeoutOrNull

class PhotosPagingSource(private val clientId:String): PagingSource<Int, Photos>() {
    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val nextPage = params.key ?: 1
        return try {
            val response = withTimeoutOrNull(TIME_OUT){
                ApiResponse.getResult { apiCall!!.getPhotos(clientId,nextPage)}
            }
            LoadResult.Page(
                data = response!!,
                prevKey = if(nextPage>1) nextPage-1 else null,
                nextKey = nextPage+1
                // as no clear pagination idea there that is why static as site contains huge page volume
            )
        }catch (e: ApiException){
            LoadResult.Error(e)
        }
    }
}
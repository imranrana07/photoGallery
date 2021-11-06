package com.imran.photogallery.data.api

import com.imran.photogallery.data.model.Photos
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int
    ):Response<MutableList<Photos>>
}
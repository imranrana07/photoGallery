package com.imran.photogallery.ui.home.viewmodel

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bumptech.glide.Glide
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.model.entities.PhotoEntity
import com.imran.photogallery.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.media.MediaScannerConnection
import android.media.MediaScannerConnection.OnScanCompletedListener

import android.os.Environment
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.net.URL


@HiltViewModel
class HomeViewModel @Inject constructor(private val photoRepository : PhotosRepository):ViewModel(){

    @ExperimentalPagingApi
    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return photoRepository.getPhotos(clientId).cachedIn(viewModelScope)
    }

}
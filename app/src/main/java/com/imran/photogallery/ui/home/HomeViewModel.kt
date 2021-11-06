package com.imran.photogallery.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel:ViewModel(){
    private val photoRepository = PhotosRepository()
    val shimmer = MutableLiveData<Int>()
    val error = MutableLiveData<String>()
    val successPhoto = MutableLiveData<String>()

    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return photoRepository.getPhotos(clientId).cachedIn(viewModelScope)
    }
}
package com.imran.photogallery.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.data.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val photoRepository : PhotosRepository):ViewModel(){

    @ExperimentalPagingApi
    fun getPhotos(clientId: String): Flow<PagingData<Photos>> {
        return photoRepository.getPhotos(clientId).cachedIn(viewModelScope)
    }

}
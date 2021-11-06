package com.qcoom.photogallery.core

interface ClickListener<T> {
    fun clickedData(data: T)
}
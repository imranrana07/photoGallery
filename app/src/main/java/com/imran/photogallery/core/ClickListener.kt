package com.imran.photogallery.core

interface ClickListener<T> {
    fun clickedData(data: T)
}
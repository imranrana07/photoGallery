package com.eduexpert.bd.data.api

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
        @SerializedName("current_page")
        val currentPage: Int,
        val data: ArrayList<T>,
        @SerializedName("last_page")
        val lastPage: Int,
//        @SerializedName("next_page_url")
//        val nextPageUrl: String,

)
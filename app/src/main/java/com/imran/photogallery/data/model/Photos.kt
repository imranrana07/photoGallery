package com.imran.photogallery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class Photos(
    @SerializedName("urls")
    @TypeConverters(UrlTypeConverter::class)
    val url: Urls,

    @PrimaryKey
    @SerializedName("id")
    val id: String

)


data class Urls(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
)

 class UrlTypeConverter {
     val gson = Gson()

     @TypeConverter()
     fun urlToString(urls: Urls): String{
         return gson.toJson(urls)
     }

     @TypeConverter()
     fun stringToUrl(photos: String): Urls{
         return gson.fromJson(photos,Urls::class.java)
     }

 }

@Entity(tableName = "photoKey")
data class PhotosKey(
    @PrimaryKey
    val id:String,

    val previous:Int?,
    val next:Int?,
)
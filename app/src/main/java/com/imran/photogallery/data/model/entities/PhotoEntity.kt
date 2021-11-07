package com.imran.photogallery.data.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable

@Entity(tableName = "photos")
data class PhotoEntity(
        @PrimaryKey
        var id:String,
        val imageUrl: String
) : Serializable
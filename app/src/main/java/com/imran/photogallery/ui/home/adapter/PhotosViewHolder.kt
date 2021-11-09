package com.imran.photogallery.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.PassData
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.utils.PHOTO_DETAILS
import com.qcoom.photogallery.databinding.ItemPhotoBinding

class PhotosViewHolder(itemView: ItemPhotoBinding): RecyclerView.ViewHolder(itemView.root) {
    val ivPhoto = itemView.ivPhoto
    val btnDownload = itemView.btnDownload
    val btnShare = itemView.btnShare

}

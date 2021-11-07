package com.imran.photogallery.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.Photos
import com.qcoom.photogallery.databinding.ItemPhotoBinding

class PhotosViewHolder(itemView: ItemPhotoBinding,private val clickedItem: ClickListener<Photos>): RecyclerView.ViewHolder(itemView.root) {
    val ivPhoto = itemView.ivPhoto

    fun itemCLick(photos: Photos){
        ivPhoto.setOnClickListener {
            clickedItem.clickedData(photos)
        }
    }
}

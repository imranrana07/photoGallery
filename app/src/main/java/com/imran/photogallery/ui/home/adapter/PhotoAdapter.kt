package com.imran.photogallery.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.PassData
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.utils.PHOTO_DETAILS
import com.imran.photogallery.utils.SAVE_PHOTO
import com.imran.photogallery.utils.SHARE_PHOTO
import com.qcoom.photogallery.R
import com.qcoom.photogallery.databinding.ItemPhotoBinding

class PhotoAdapter(private val clickedItem: ClickListener<PassData<Photos>>):PagingDataAdapter<Photos, PhotosViewHolder>(
    DiffUtils
) {
    private lateinit var context: Context

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photos = getItem(position) as Photos
        Glide.with(context).load(photos.urls.regular).placeholder(R.drawable.error).into(holder.ivPhoto)

        holder.ivPhoto.setOnClickListener {
            clickedItem.clickedData(PassData(PHOTO_DETAILS,photos))
        }

        holder.btnDownload.setOnClickListener {
            clickedItem.clickedData(PassData(SAVE_PHOTO,photos))
        }

        holder.btnShare.setOnClickListener {
            clickedItem.clickedData(PassData(SHARE_PHOTO,photos))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        context = parent.context
        val view = ItemPhotoBinding.inflate(LayoutInflater.from(context),parent,false)
        return PhotosViewHolder(view)
    }

    object DiffUtils: DiffUtil.ItemCallback<Photos>(){
        override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem == newItem
        }

    }
}
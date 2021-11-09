package com.imran.photogallery.ui.single_photo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.imran.photogallery.utils.BUNDLE_VALUE
import com.qcoom.photogallery.R
import com.qcoom.photogallery.databinding.FragmentSinglePhotoBinding


class SinglePhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val binding = FragmentSinglePhotoBinding.inflate(layoutInflater,container,false)
        val photoUrl = arguments?.getString(BUNDLE_VALUE)
        binding.imageUrl = photoUrl
        return binding.root
    }

}
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
//    private lateinit var binding: FragmentSinglePhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
//        binding = FragmentSinglePhotoBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        val binding = FragmentSinglePhotoBinding.inflate(layoutInflater,container,false)
        val photoUrl = arguments?.getString(BUNDLE_VALUE)
        binding.imageUrl = photoUrl
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Glide.with(requireContext()).load(photoUrl).error(R.drawable.error).into(binding.)
    }

}
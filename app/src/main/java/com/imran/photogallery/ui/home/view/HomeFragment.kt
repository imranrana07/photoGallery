package com.imran.photogallery.ui.home.view

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.ui.home.viewmodel.HomeViewModel
import com.imran.photogallery.ui.home.adapter.PhotoAdapter
import com.qcoom.photogallery.BuildConfig.ACCESS_KEY
import com.qcoom.photogallery.R
import com.qcoom.photogallery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.util.*
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import com.imran.photogallery.data.model.PassData
import com.imran.photogallery.utils.*
import kotlinx.coroutines.*
import retrofit2.http.Url
import java.net.URL
import android.app.DownloadManager
import android.content.Context
import android.content.ContextWrapper

import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.EnvironmentCompat


@AndroidEntryPoint
class HomeFragment : Fragment(),ClickListener<PassData<Photos>> {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val adapter = PhotoAdapter(this)
        val layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvPhotos.layoutManager = layoutManager
        binding.rvPhotos.adapter = adapter

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading){
                binding.shimmer.itemShimmer.visibility = VISIBLE
                binding.shimmer.itemShimmer.startShimmerAnimation()
            }else {
                if (it.refresh is LoadState.Error){
                    toast((it.refresh as LoadState.Error).error.localizedMessage!!)
                }
                binding.shimmer.itemShimmer.stopShimmerAnimation()
                binding.shimmer.itemShimmer.visibility = GONE
            }
        }

        this.lifecycleScope.launch {
            viewModel.getPhotos(ACCESS_KEY).collectLatest {
                adapter.submitData(viewLifecycleOwner.lifecycle,it)
            }
        }

    }

    override fun clickedData(data: PassData<Photos>) {
        val imageUrl = data.item.urls.regular
        when (data.btn) {
            PHOTO_DETAILS -> {
                val bundle = bundleOf(BUNDLE_VALUE to imageUrl)
                findNavController().navigate(R.id.singlePhotoFragment, bundle)
            }
            SHARE_PHOTO -> sharePhoto(imageUrl)
            else -> { // save image
                saveImage(imageUrl)
            }
        }
    }

    private fun sharePhoto(uri: String){
        val imageUrl = URL(uri)
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUrl)
            type = "image/*"
        }
        context?.startActivity(
            Intent.createChooser(
                shareIntent,
                "Share Photo"
            )
        )
    }


}
package com.imran.photogallery.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.ui.home.adapter.PhotoAdapter
import com.imran.photogallery.utils.BUNDLE_VALUE
import com.imran.photogallery.utils.GONE
import com.imran.photogallery.utils.VISIBLE
import com.imran.photogallery.utils.toast
import com.qcoom.photogallery.BuildConfig.ACCESS_KEY
import com.qcoom.photogallery.R
import com.qcoom.photogallery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(),ClickListener<Photos> {
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

    override fun clickedData(data: Photos) {
        val bundle = bundleOf(BUNDLE_VALUE to data.urls.regular)
        findNavController().navigate(R.id.singlePhotoFragment,bundle)
    }
}
package com.imran.photogallery.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.imran.photogallery.core.ClickListener
import com.imran.photogallery.data.model.Photos
import com.imran.photogallery.ui.home.adapter.PhotoAdapter
import com.imran.photogallery.utils.GONE
import com.imran.photogallery.utils.VISIBLE
import com.qcoom.photogallery.BuildConfig.ACCESS_KEY
import com.qcoom.photogallery.R
import com.qcoom.photogallery.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment(),ClickListener<Photos> {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
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
            }else{
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

    }


}
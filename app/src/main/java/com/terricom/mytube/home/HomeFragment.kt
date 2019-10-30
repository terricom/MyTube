package com.terricom.mytube.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.terricom.mytube.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentHomeBinding.inflate (inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerVideo.adapter = VideoAdapter(viewModel)

        viewModel.videoList.observe(this, Observer {
            (binding.recyclerVideo.adapter as VideoAdapter).submitList(it)
        })


        return binding.root
    }
}
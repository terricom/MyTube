package com.terricom.mytube.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.terricom.mytube.data.TestResponse
import com.terricom.mytube.data.UserManager
import com.terricom.mytube.data.Video
import com.terricom.mytube.data.Videos
import com.terricom.mytube.databinding.FragmentHomeBinding
import com.google.gson.reflect.TypeToken
import android.content.Context.MODE_PRIVATE
import java.lang.reflect.Type


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

        if (UserManager.videoList.isNullOrEmpty()){

            viewModel.videoList.observe(this, Observer {
                (binding.recyclerVideo.adapter as VideoAdapter).submitList(it)
            })
        } else {

            UserManager.videoList?.let {storage ->

                val type = object : TypeToken<List<Video>>() {
                }.type

                val videos: List<Video> = Gson().fromJson(storage, type)

                videos.let {
                    (binding.recyclerVideo.adapter as VideoAdapter).submitList(it)
                }
            }
        }

        return binding.root
    }
}
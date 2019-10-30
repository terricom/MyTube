package com.terricom.mytube.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.terricom.mytube.databinding.FragmentHomeBinding
import com.terricom.mytube.databinding.FragmentSettingBinding
import com.terricom.mytube.home.HomeViewModel

class SettingFragment: Fragment() {

    private val viewModel: SettingViewModel by lazy {
        ViewModelProviders.of(this).get(SettingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentSettingBinding.inflate (inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}
package com.terricom.mytube.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.terricom.mytube.databinding.FragmentTimerBinding

class TimerFragment: Fragment() {

    private val viewModel: TimerViewModel by lazy {
        ViewModelProviders.of(this).get(TimerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentTimerBinding.inflate (inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }


}
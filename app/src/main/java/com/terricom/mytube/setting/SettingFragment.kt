package com.terricom.mytube.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.terricom.mytube.data.Setting
import com.terricom.mytube.databinding.FragmentSettingBinding
import java.util.logging.Logger

class SettingFragment: Fragment() {

    private val viewModel: SettingViewModel by lazy {
        ViewModelProviders.of(this).get(SettingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentSettingBinding.inflate (inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.ifAutoPlay.observe(this, Observer {
            Log.i("Terri", " viewModel.ifAutoPlay.observe = $it")
            binding.switchAutoPlay.setOnCheckedChangeListener { _, _ ->
                viewModel.setAutoPlay(it)
            }
        })

        viewModel.ifSubtitle.observe(this, Observer {
            binding.switchSubtitle.setOnCheckedChangeListener { _, _ ->
                viewModel.setSubtitle(it)
            }
        })

        viewModel.ifPauseWhen.observe(this, Observer {
            Log.i("Terri", " viewModel.ifAutoPlay.observe = $it")
            binding.switchPauseWhen.setOnCheckedChangeListener { _, _ ->
                viewModel.setPauseWhen(it)
            }
        })

        viewModel.ifRecommended.observe(this, Observer {
            binding.switchRecommended.setOnCheckedChangeListener { _, _ ->
                viewModel.setRecommended(it)
            }
        })

        viewModel.ifLearning.observe(this, Observer {
            binding.switchLearning.setOnCheckedChangeListener { _, _ ->
                viewModel.setLearning(it)
            }
        })

        return binding.root
    }
}
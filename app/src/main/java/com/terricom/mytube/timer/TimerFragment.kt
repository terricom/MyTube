package com.terricom.mytube.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.terricom.mytube.MyTubeApp
import com.terricom.mytube.R
import com.terricom.mytube.databinding.FragmentTimerBinding


class TimerFragment: Fragment() {

    private val viewModel: TimerViewModel by lazy {
        ViewModelProviders.of(this).get(TimerViewModel::class.java)
    }
    private var cdt: CountDownTimer?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentTimerBinding.inflate (inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var leftTime = binding.timerTime.text.toString().toLong()

        fun initCountDownTimer(remainTime: Long){

            cdt = object : CountDownTimer(remainTime*1000, 1000) {

                override fun onFinish() {
                }

                override fun onTick(millisUntilFinished: Long) {

                    leftTime = millisUntilFinished/1000
                    binding.timerTime.setText("${millisUntilFinished/1000}", TextView.BufferType.EDITABLE)
                }
            }.start()
        }

        binding.buttonPlay.setOnClickListener {

            initCountDownTimer(leftTime)
            binding.buttonPlay.text = MyTubeApp.applicationContext().getString(R.string.timer_on_resume)
        }

        binding.buttonStop.setOnClickListener {
            cdt?.let {
                it.cancel()
            }
        }
        return binding.root
    }


}
package com.terricom.mytube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var title: TextView
    val viewModel : MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.title)
        setupNavController()
        setting.setOnClickListener {
            Log.i("Navigation", "Clicked")
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_settingFragment)
        }
        timer.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_timerFragment)
        }
    }

    private fun setupNavController() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener {
                navController: NavController, _: NavDestination, _: Bundle? ->
            viewModel.currentFragmentType.value = when (navController.currentDestination?.id) {
                R.id.homeFragment -> CurrentFragmentType.HOME
                R.id.settingFragment -> CurrentFragmentType.SETTING
                R.id.timerFragment -> CurrentFragmentType.TIMER
                else -> viewModel.currentFragmentType.value
            }
        }
        viewModel.currentFragmentType.observe(this, Observer {
            Log.i("Terri", "viewModel.currentFragmentType.observe = ${it.value}")
            title.text = it.value

            when(it.value){
                MyTubeApp.applicationContext().getString(R.string.title_home) ->{
                    back.visibility = View.GONE
                    setting.visibility = View.VISIBLE
                    timer.visibility = View.VISIBLE
                    setting.setOnClickListener {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_settingFragment)
                    }
                    timer.setOnClickListener {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_timerFragment)
                    }
                }
                else -> {
                    back.visibility = View.VISIBLE
                    setting.visibility = View.GONE
                    timer.visibility = View.GONE
                    back.setOnClickListener {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_homeFragment)
                    }
                }
            }
        })
    }
}

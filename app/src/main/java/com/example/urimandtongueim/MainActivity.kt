package com.example.urimandtongueim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import androidx.fragment.app.FragmentManager

import com.example.urimandtongueim.model.DataCache




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = this.supportFragmentManager
        if (!DataCache.isUserLoggedIn()) {
            val loginFragment: LoginFragment = LoginFragment()
            val args = Bundle()
            loginFragment.arguments = args
            fm.beginTransaction()
                .add(R.id.fragmentContainer, loginFragment)
                .commit()
        }
        else {
            val homeFragment: HomeFragment = HomeFragment()
            val args = Bundle()
            homeFragment.arguments = args
            fm.beginTransaction()
                .add(R.id.fragmentContainer, homeFragment)
                .commit()
        }

    }
}
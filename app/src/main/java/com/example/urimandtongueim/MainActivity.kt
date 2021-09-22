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

    private var isLoggedIn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = this.supportFragmentManager
        if (!isLoggedIn) {
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

//        val buttonAddLanguage = findViewById<Button>(R.id.addLanguage)
//        buttonAddLanguage.setOnClickListener {
//            val intent = Intent(this, AddLanguageActivity::class.java)
//            startActivity(intent)
//        }
//
//        val buttonSpanish = findViewById<Button>(R.id.languageSpanish)
//        buttonSpanish.setOnClickListener {
//            val intent = Intent(this, StudyActivity::class.java)
//            startActivity(intent)
//        }
//
//        val buttonUserSettings = findViewById<Button>(R.id.user)
//        buttonUserSettings.setOnClickListener {
//            val intent = Intent(this, UserSettingsActivity::class.java)
//            startActivity(intent)
//        }
    }
}
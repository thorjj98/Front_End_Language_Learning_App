package com.example.urimandtongueim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddLanguage = findViewById<Button>(R.id.addLanguage)
        buttonAddLanguage.setOnClickListener {
            val intent = Intent(this, AddLanguageActivity::class.java)
            startActivity(intent)
        }

        val buttonSpanish = findViewById<Button>(R.id.languageSpanish)
        buttonSpanish.setOnClickListener {
            val intent = Intent(this, StudyActivity::class.java)
            startActivity(intent)
        }

        val buttonUserSettings = findViewById<Button>(R.id.user)
        buttonUserSettings.setOnClickListener {
            val intent = Intent(this, UserSettingsActivity::class.java)
            startActivity(intent)
        }
    }
}
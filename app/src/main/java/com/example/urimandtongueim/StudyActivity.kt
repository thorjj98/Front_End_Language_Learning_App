package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        //to do connect to scripture activity
        //
        val buttonBookOfMormon = findViewById<Button>(R.id.bookOfMormon)
        buttonBookOfMormon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonWordTracker = findViewById<Button>(R.id.wordTracker)
        buttonWordTracker.setOnClickListener {
            val intent = Intent(this, WordTrackerActivity::class.java)
            startActivity(intent)
        }
    }
}
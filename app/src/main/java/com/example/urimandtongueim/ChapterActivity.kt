package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        val buttonChapter1 = findViewById<Button>(R.id.chapterOne)
        buttonChapter1.setOnClickListener {
            val intent = Intent(this, VersesActivity::class.java)
            startActivity(intent)
        }

    }
}
package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ScriptureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scripture)

        val button1Nephi = findViewById<Button>(R.id.FirstNephi)
        //button1Nephi.setText(i.name)
        button1Nephi.setOnClickListener {
            val intent = Intent(this, ChapterActivity::class.java)
            startActivity(intent)
        }
        /*
        var parser: JsonParser = JsonParser()
        var books = parser.getBooks(this)
        if (books != null) {
            for (i in books){

            }
        }*/
    }
}
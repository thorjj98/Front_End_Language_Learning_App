package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.urimandtongueim.model.JsonParser

class ChapterActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        val book = intent.getStringExtra("book")
        val standardWork = intent.getIntExtra("standardWork", -1)

        val layout = findViewById<LinearLayout>(R.id.chapterLayout)
        val parser = JsonParser()
        val chapters = book?.let { parser.getChapters(this, it, standardWork, 1) }
        if (chapters != null) {
            var j = 1
            for (i in chapters){
                val button = Button(this)
                button.text= "Chapter $j"
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(40, 40, 40, 0)
                val chapterIndex = j - 1
                button.setOnClickListener {
                    val intent = Intent(this, VersesActivity::class.java)
                    intent.putExtra("chapter", chapterIndex)
                    intent.putExtra("book", book)
                    intent.putExtra("standardWork", standardWork)
                    startActivity(intent)
                }
                button.setBackgroundColor(Color.parseColor("#356ec9"))
                button.setTextColor(Color.parseColor("#ffffff"))
                button.layoutParams = params
                layout.addView(button)
                j++
            }
        }

    }
}
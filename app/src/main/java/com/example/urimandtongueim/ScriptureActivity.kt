package com.example.urimandtongueim

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.urimandtongueim.model.JsonParser

class ScriptureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scripture)

        val layout = findViewById<LinearLayout>(R.id.scriptureLayout)
        val parser = JsonParser()
        val books = parser.getBooks(this, 0, 1)
        if (books != null) {
            for (i in books){
                val button = Button(this)
                button.text=i.name
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(40, 40, 40, 0)
                button.setOnClickListener {
                    val intent = Intent(this, ChapterActivity::class.java)
                    intent.putExtra("book", i.id)
                    intent.putExtra("standardWork", 0)
                    startActivity(intent)
                }
                button.setBackgroundColor(Color.parseColor("#356ec9"))
                button.setTextColor(Color.parseColor("#ffffff"))
                button.layoutParams = params
                layout.addView(button)
            }
        }
    }
}
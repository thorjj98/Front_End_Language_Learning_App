package com.example.urimandtongueim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.urimandtongueim.model.requests.JsonRequest
import com.example.urimandtongueim.model.service.JsonService

class AddLanguageActivity : AppCompatActivity() {

    val jsonService = JsonService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_language)

        val buttonAddLanguage = findViewById<Button>(R.id.language1)
        buttonAddLanguage.setOnClickListener {
            val response = jsonService.getJson(JsonRequest("English"))
            if (response.isSuccess()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
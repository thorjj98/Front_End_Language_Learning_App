package com.example.urimandtongueim

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.FileRequest
import com.example.urimandtongueim.model.requests.LanguageRequest
import com.example.urimandtongueim.model.requests.StatisticRequest
import com.example.urimandtongueim.model.service.FileService
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.service.StatisticService

class AddLanguageActivity : AppCompatActivity() {

    val jsonService = FileService()
    var languageService = LanguageService()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_language)

        val layout = findViewById<LinearLayout>(R.id.addLanguage)

        val response = languageService.getLanguages(LanguageRequest())
        if (response != null) {
            if (response.isSuccess()){
                for (language in response.getLanguages()){
                            val button = Button(this)
                            button.text=language
                            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            params.setMargins(40, 40, 40, 0)
                            button.setOnClickListener {
                                val response = jsonService.getJson(FileRequest("English"))
                                if (response != null) {
                                    if (response.isSuccess()) {
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }
                            button.setBackgroundColor(Color.parseColor("#356ec9"))
                            button.setTextColor(Color.parseColor("#ffffff"))
                            button.layoutParams = params
                            layout.addView(button)
                }
            }
        }
    }
}
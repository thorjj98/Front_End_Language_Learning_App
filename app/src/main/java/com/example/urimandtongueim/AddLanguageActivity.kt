package com.example.urimandtongueim

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
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
import com.example.urimandtongueim.model.responses.LanguageResponse
import com.example.urimandtongueim.model.service.FileService
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.service.StatisticService
import model.Language

class AddLanguageActivity : AppCompatActivity() {

    val jsonService = FileService()
    private val languageAsyncTask = LanguageTask()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_language)

        languageAsyncTask.execute()
    }

    fun setLanguages(languages: MutableList<Language>){
        val layout = findViewById<LinearLayout>(R.id.addLanguage)

        val languageNames: Array<String?> = arrayOfNulls(languages.size)
        var i = 0
        for (language in languages) {
            languageNames[i] = language.name
            i += 1
        }
        for (language in languageNames){
            val button = Button(this)
            button.text=language
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(40, 40, 40, 0)
            button.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                /*val response = jsonService.getJson(FileRequest("English"))
                if (response != null) {
                    if (response.isSuccess()) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }*/
            }
            button.setBackgroundColor(Color.parseColor("#356ec9"))
            button.setTextColor(Color.parseColor("#ffffff"))
            button.layoutParams = params
            layout.addView(button)
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class LanguageTask: AsyncTask<LanguageRequest, Void, LanguageResponse>(){
        @RequiresApi(Build.VERSION_CODES.N)
        override fun doInBackground(vararg params: LanguageRequest?): LanguageResponse? {
            val languageService = LanguageService()
            return languageService.getLanguages(LanguageRequest())
        }

        override fun onPostExecute(result: LanguageResponse?) {
            super.onPostExecute(result)
            if (result != null && result.isSuccess()) {
                result.getLanguages()?.let { setLanguages(it) }
            }
        }
    }
}
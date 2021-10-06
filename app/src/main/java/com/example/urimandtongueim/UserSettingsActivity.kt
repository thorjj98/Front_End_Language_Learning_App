package com.example.urimandtongueim

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class UserSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)

        val languageArray = arrayListOf("English", "Spanish")

        val nativeLanguageAdapter: ArrayAdapter<*>
        val nativeLanguageSpinner: Spinner = findViewById(R.id.spinner)
        nativeLanguageAdapter = this?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                languageArray
            )
        }!!
        nativeLanguageSpinner.adapter = nativeLanguageAdapter

    }

}
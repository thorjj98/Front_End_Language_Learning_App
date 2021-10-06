package com.example.urimandtongueim.model.requests

class JsonRequest(languageInput: String) {
    val language = languageInput

    @JvmName("getLanguage1")
    fun getLanguage(): String{
        return language
    }
}

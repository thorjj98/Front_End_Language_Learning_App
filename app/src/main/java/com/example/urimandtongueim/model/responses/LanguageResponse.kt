package com.example.urimandtongueim.model.responses

class LanguageResponse(successInput: Boolean, languageInput: Array<String>, fileInput: Array<String>) {

    var success: Boolean = successInput
    var languages: Array<String> = languageInput
    var files: Array<String> = fileInput

    fun isSuccess(): Boolean {
        return success
    }

    @JvmName("getLanguages1")
    fun getLanguages(): Array<String> {
        return languages
    }

    @JvmName("getFiles1")
    fun getFiles(): Array<String> {
        return files
    }

}
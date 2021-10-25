package com.example.urimandtongueim.model.responses

import model.Language

class LanguageResponse(success: Boolean, var languages: MutableList<Language>?): Response(success) {

    fun isSuccess(): Boolean {
        return success
    }

    @JvmName("getLanguages1")
    fun getLanguages(): MutableList<Language>? {
        return languages
    }
//
//    @JvmName("getFiles1")
//    fun getFiles(): Array<String> {
//        return files
//    }

}
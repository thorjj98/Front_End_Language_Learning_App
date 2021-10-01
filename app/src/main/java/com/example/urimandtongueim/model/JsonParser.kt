package com.example.urimandtongueim.model

import com.example.urimandtongueim.R
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.io.IOException

class JsonParser {


    data class Scripture(
        val id: String,
        val name: String,
        val books: Array<Books>,
        )

    data class Books(
        val id: String,
        val name: String,
        val chapters: Array<Books>
    )
    data class Verse(
        val verses: Array<String>
    )

    fun getBooks(): Array<Books>? {
        val jsonString: String
        try {
            jsonString = File("app/src/main/res/scriptures/eng.json").readLines().toString()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        var gson = Gson()
        var scripture = gson.fromJson(jsonString, Scripture::class.java)
        var books : MutableList<Books> = scripture.books.toMutableList()
        return books.toTypedArray()
    }

    fun getChapters(){

    }

    fun getVerses(){

    }
}
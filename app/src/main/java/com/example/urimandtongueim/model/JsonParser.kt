package com.example.urimandtongueim.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import androidx.core.content.ContentProviderCompat.requireContext
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

    @SuppressLint("SdCardPath")
    fun getBooks(context: Context): Array<Books>? {
        val jsonString: String
        try {
            val downloadFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (downloadFolder != null) {
                jsonString = File(downloadFolder?.path + "/eng.json").readLines().toString()
                var gson = Gson()
                var scripture = gson.fromJson(jsonString, Scripture::class.java)
                var books : MutableList<Books> = scripture.books.toMutableList()
                return books.toTypedArray()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return null
    }

    fun getChapters(){

    }

    fun getVerses(){

    }
}
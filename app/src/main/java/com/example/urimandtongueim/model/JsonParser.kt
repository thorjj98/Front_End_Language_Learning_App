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


//    data class StandardWorks(
//        //val id: String,
//        //val name: String,
//        val scriptures: Array<Scripture>
//        )

    data class Scripture(
        //val id: String,
        //val name: String,
        val books: Array<Book>
    )

    data class Book(
        val id: String,
        val name: String,
        val books: Array<SmallerBooks>
    )
    data class SmallerBooks(
        val id: String,
        val name: String,
        val chapters: Array<Array<String>>
    )

    @SuppressLint("SdCardPath")
    fun getBooks(context: Context): Array<Book>? {
        val jsonString: String
        try {
            val downloadFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (downloadFolder != null) {
                val path = context.filesDir.absolutePath
                jsonString = File("$path/eng.json").readLines().toString()
                val gson = Gson()
                val scriptures = gson.fromJson(jsonString, Array<Scripture>::class.java)
                val books : MutableList<Book> = scriptures[0].books.toMutableList()
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
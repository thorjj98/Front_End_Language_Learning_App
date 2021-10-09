package com.example.urimandtongueim.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import java.io.File
import java.io.IOException

class JsonParser {

    data class Scripture(
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
    data class ScriptureTranslation(
        val books: Array<BookTranslation>
    )
    data class BookTranslation(
        val id: String,
        val name: String,
        val books: Array<SmallerBooksTranslation>
    )
    data class SmallerBooksTranslation(
        val id: String,
        val name: String,
        val chapters: Array<Array<Array<Array<Int>>>>
    )

    @SuppressLint("SdCardPath")
    fun getBooks(context: Context, standardWork: Int, mapId: Int): Array<SmallerBooks>? {
        val jsonString: String
        try {
            val downloadFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (downloadFolder != null) {
                val path = context.filesDir.absolutePath
                if (mapId == 0) jsonString = File("$path/eng.json").readLines().toString()
                else if (mapId == 1) jsonString = File("$path/spa.json").readLines().toString()
                else return null
                val gson = Gson()
                val scripture = gson.fromJson(jsonString, Array<Scripture>::class.java)
                val books : MutableList<SmallerBooks> = scripture[0].books[0].books.toMutableList()
                return books.toTypedArray()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return null
    }

    fun getChapters(context: Context, book: String, standardWork: Int, mapId: Int): Array<Array<String>>? {
        val books = getBooks(context, standardWork, mapId)
        if (books != null) {
            for (i in books){
                if (i.id == book){
                    return i.chapters
                }
            }
        }
        return null
    }

    fun getVerses(context: Context, chapter: Int, book: String, standardWork: Int, mapId: Int): Array<String>? {
        val chapters = getChapters(context, book, standardWork, mapId)
        if (chapters != null){
            return chapters[chapter]
        }
        return null
    }

    fun getMapping(context: Context, chapter: Int, book: String, standardWork: Int, mapId: Int): Array<Array<Array<Int>>>?{
        val jsonString: String
        try {
            val downloadFolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            if (downloadFolder != null) {
                val path = context.filesDir.absolutePath
                jsonString = File("$path/eng-spa.json").readLines().toString()
                val gson = Gson()
                val scripture = gson.fromJson(jsonString, Array<ScriptureTranslation>::class.java)
                val books : MutableList<SmallerBooksTranslation> = scripture[0].books[0].books.toMutableList()
                for (i in books){
                    if (i.id == book){
                        return i.chapters[chapter]
                    }
                }
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return null
    }
}
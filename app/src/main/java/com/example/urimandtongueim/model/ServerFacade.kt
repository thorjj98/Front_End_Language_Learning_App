package com.example.urimandtongueim.model

import com.example.urimandtongueim.model.requests.*
import com.example.urimandtongueim.model.responses.*
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class ServerFacade {

    fun login(request: LoginRequest): LoginResponse? {
        val jsonString = Json.encodeToString(request)
        val connection = URL("http://10.0.2.2:8080/login").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "Post"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            writeStringToOutputStream(jsonString, connection.outputStream)
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, LoginResponse::class.java)
        }
        return null
    }

    fun register(request: RegisterRequest): RegisterResponse? {
        val jsonString = Json.encodeToString(request)
        val connection = URL("http://10.0.2.2:8080/register").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "Post"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            writeStringToOutputStream(jsonString, connection.outputStream)
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, RegisterResponse::class.java)
        }
        return null
    }

    fun getJson(request: FileRequest): FileResponse? {
        val jsonString = Json.encodeToString(request)
        val connection = URL("http://10.0.2.2:8080/jsons").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "Get"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            writeStringToOutputStream(jsonString, connection.outputStream)
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, FileResponse::class.java)
        }
        return null
    }

    fun getLanguages(request: LanguageRequest): LanguageResponse? {
        val jsonString = Json.encodeToString(request)
        val connection = URL("http://10.0.2.2:8080/language").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            writeStringToOutputStream(jsonString, connection.outputStream)
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, LanguageResponse::class.java)
        }
        return null
    }

    fun getStatsitics(request: StatisticRequest): StatisticResponse? {
        val jsonString = Json.encodeToString(request)
        val connection = URL("http://10.0.2.2:8080/stats").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "Get"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            writeStringToOutputStream(jsonString, connection.outputStream)
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, StatisticResponse::class.java)
        }
        return null
    }

    private fun readStringFromInputStream(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        val streamReader = InputStreamReader(inputStream)
        val buf = CharArray(1024)
        var len: Int

        while (0 < streamReader.read(buf).also { len = it }) {
            stringBuilder.append(buf, 0, len)
        }

        inputStream.close()
        return stringBuilder.toString()
    }

    private fun writeStringToOutputStream(str: String, outputStream: OutputStream) {
        val streamWriter = OutputStreamWriter(outputStream)
        val bufferedWriter = BufferedWriter(streamWriter)
        bufferedWriter.write(str)
        bufferedWriter.flush()
    }

}
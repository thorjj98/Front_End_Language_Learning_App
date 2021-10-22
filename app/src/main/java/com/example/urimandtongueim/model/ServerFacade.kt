package com.example.urimandtongueim.model

import com.example.urimandtongueim.model.requests.*
import com.example.urimandtongueim.model.responses.*
import com.google.gson.Gson
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class ServerFacade {

    fun login(request: LoginRequest): LoginResponse? {
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val connection = URL("http://10.0.2.2:8080/login").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "POST"
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        writeStringToOutputStream(jsonString, connection.outputStream)
        val responseCode = connection.responseCode
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val jsonResponse: String = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            return gson.fromJson(jsonResponse, LoginResponse::class.java)
        }
        return null
    }

    fun register(request: RegisterRequest): RegisterResponse? {
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val connection = URL("http://10.0.2.2:8080/register").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "POST"
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        writeStringToOutputStream(jsonString, connection.outputStream)
        val responseCode = connection.responseCode
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
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val connection = URL("http://10.0.2.2:8080/jsons").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        writeStringToOutputStream(jsonString, connection.outputStream)
        val responseCode = connection.responseCode
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
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val connection = URL("http://10.0.2.2:8080/language").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        writeStringToOutputStream(jsonString, connection.outputStream)
        val responseCode = connection.responseCode
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
        val gson = Gson()
        val jsonString = gson.toJson(request)
        val connection = URL("http://10.0.2.2:8080/stats").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.doInput = true
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json")
        writeStringToOutputStream(jsonString, connection.outputStream)
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
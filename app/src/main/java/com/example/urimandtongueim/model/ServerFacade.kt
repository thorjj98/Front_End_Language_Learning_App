package com.example.urimandtongueim.model

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.urimandtongueim.model.requests.*
import com.example.urimandtongueim.model.responses.*
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ServerFacade {
//AsyncTask<Void, Void, String>
    fun login(request: LoginRequest): LoginResponse{
        val response = LoginResponse(true)
        return response
    }

    fun register(request: RegisterRequest): RegisterResponse{
        val response = RegisterResponse(true)
        return response
    }

    fun getJson(request: FileRequest): FileResponse{
        val response = FileResponse(true)
        return response
    }

    lateinit var languageResponse: LanguageResponse

    @RequiresApi(Build.VERSION_CODES.N)
    fun getLanguages(request: LanguageRequest): LanguageResponse? {
        val connection = URL("http://10.0.2.2:8080/language").openConnection() as HttpURLConnection
        connection.readTimeout = 5000
        connection.requestMethod = "GET"
        connection.connect()
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val jsonResponse: String? = readStringFromInputStream(connection.inputStream)
            val gson = Gson()
            val response = gson.fromJson(jsonResponse, LanguageResponse::class.java)
            return response
        }
        return null
    }

    fun getStatsitics(request: StatisticRequest): StatisticResponse{
        val response = StatisticResponse(true)
        return response
    }

    private fun readStringFromInputStream(inputStream: InputStream): String? {
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


}
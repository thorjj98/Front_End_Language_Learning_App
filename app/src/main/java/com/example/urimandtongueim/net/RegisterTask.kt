package com.example.urimandtongueim.net

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.urimandtongueim.model.User
import com.example.urimandtongueim.model.requests.LanguageRequest
import com.example.urimandtongueim.model.responses.LanguageResponse
import com.example.urimandtongueim.model.service.LanguageService
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.responses.RegisterResponse
import com.example.urimandtongueim.model.service.RegisterService


@SuppressLint("StaticFieldLeak")
class RegisterTask @RequiresApi(Build.VERSION_CODES.N) constructor(l: Listener?) :
    AsyncTask<RegisterRequest, Void, RegisterResponse>() {
    @RequiresApi(Build.VERSION_CODES.N)
    private var listener: Listener? = l

    interface Listener {
        fun onPostExecute(result: RegisterResponse?)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onPostExecute(response: RegisterResponse) {
        listener!!.onPostExecute(response)
    }

    override fun doInBackground(vararg request: RegisterRequest): RegisterResponse {
        val registerService = RegisterService()
        return registerService.register(request[0])
    }

}
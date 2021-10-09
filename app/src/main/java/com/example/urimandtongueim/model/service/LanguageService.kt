package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.LanguageRequest
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.responses.LanguageResponse
import com.example.urimandtongueim.model.responses.LoginResponse

class LanguageService {

    var server = ServerFacade()

    fun getLanguages(request: LanguageRequest): LanguageResponse {
        var response: LanguageResponse = server.getLanguages(request)
        return response
    }
}
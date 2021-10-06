package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.JsonRequest
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.responses.JsonResponse
import com.example.urimandtongueim.model.responses.LoginResponse

class JsonService {
    var server = ServerFacade()

    fun getJson(request: JsonRequest): JsonResponse {
        var response: JsonResponse = server.getJson(request)
        return response
    }
}
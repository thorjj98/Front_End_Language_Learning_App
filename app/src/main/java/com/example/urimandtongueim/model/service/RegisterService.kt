package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.responses.LoginResponse
import com.example.urimandtongueim.model.responses.RegisterResponse

class RegisterService {

    var server = ServerFacade()

    fun register(request: RegisterRequest): RegisterResponse? {
        var response: RegisterResponse? = server.register(request)
        return response
    }
}
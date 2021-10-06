package com.example.urimandtongueim.model

import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.requests.RegisterRequest
import com.example.urimandtongueim.model.responses.LoginResponse
import com.example.urimandtongueim.model.responses.RegisterResponse

class ServerFacade {

    fun login(request: LoginRequest): LoginResponse{
        val response = LoginResponse(true)
        return response
    }

    fun register(request: RegisterRequest): RegisterResponse{
        val response = RegisterResponse(true)
        return response
    }
}
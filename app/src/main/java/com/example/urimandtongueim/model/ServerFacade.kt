package com.example.urimandtongueim.model

import com.example.urimandtongueim.model.requests.*
import com.example.urimandtongueim.model.responses.*

class ServerFacade {

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

    fun getLanguages(request: LanguageRequest): LanguageResponse{
        val response = LanguageResponse(true, arrayOf("English", "Spanish"))
        return response
    }

    fun getStatsitics(request: StatisticRequest): StatisticResponse{
        val response = StatisticResponse(true)
        return response
    }

}
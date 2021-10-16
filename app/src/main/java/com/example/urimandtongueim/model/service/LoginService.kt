package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.LoginRequest
import com.example.urimandtongueim.model.responses.LoginResponse

class LoginService {

    var server = ServerFacade()

    fun login(request: LoginRequest): LoginResponse? {
        var response: LoginResponse? = server.login(request)
        return response
    }

}
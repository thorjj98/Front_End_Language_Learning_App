package com.example.urimandtongueim.model.responses

class LoginResponse(successInput: Boolean) {

    var success: Boolean = successInput

    fun isSuccess(): Boolean {
        return success
    }

}
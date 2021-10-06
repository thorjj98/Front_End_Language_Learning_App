package com.example.urimandtongueim.model.responses

class RegisterResponse(successInput: Boolean) {

    var success: Boolean = successInput

    fun isSuccess(): Boolean {
        return success
    }

}
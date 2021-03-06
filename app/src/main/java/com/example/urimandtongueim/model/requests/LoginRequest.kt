package com.example.urimandtongueim.model.requests

import kotlinx.serialization.Serializable

class LoginRequest(userNameInput: String, passwordInput: String) {

    var username = userNameInput
    var password = passwordInput

    @JvmName("getUsername1")
    fun getUsername(): String {
        return username
    }

    @JvmName("getPassword1")
    fun getPassword(): String {
        return password
    }

}
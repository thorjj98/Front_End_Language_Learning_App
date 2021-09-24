package com.example.urimandtongueim.model

object DataCache {

    var isLoggedIn: Boolean = false
    var username = ""
    var password = ""

    fun isUserLoggedIn(): Boolean {
        return isLoggedIn
    }

    fun setLoggedInStatus(loggedInBool: Boolean) {
        isLoggedIn = loggedInBool
    }
}
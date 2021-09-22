package com.example.urimandtongueim.model

class DataCache {
    private var instance: DataCache? = null

    fun getInstance(): DataCache? {
        if (instance == null) {
            instance = DataCache()
        }
        return instance
    }

    private var isLoggedIn = false

    fun isLoggedIn(): Boolean {
        return isLoggedIn
    }

    fun setLoggedIn(loggedIn: Boolean) {
        isLoggedIn = loggedIn
    }
}
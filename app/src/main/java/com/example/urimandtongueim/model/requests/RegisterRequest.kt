package com.example.urimandtongueim.model.requests

class RegisterRequest(userNameInput: String, passwordInput: String, nativeLanguageInput: String, learningLanguageInput: String) {

    var username = userNameInput
    var password = passwordInput
    var nativeLanguage = nativeLanguageInput
    var learningLanguage = learningLanguageInput

    @JvmName("getUsername1")
    fun getUsername(): String {
        return username
    }

    @JvmName("getPassword1")
    fun getPassword(): String {
        return password
    }

    @JvmName("getLearningLanguage1")
    fun getLearningLanguage(): String {
        return learningLanguage
    }

    @JvmName("getNativeLanguage1")
    fun getNativeLanguage(): String {
        return nativeLanguage
    }

}
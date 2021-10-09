package com.example.urimandtongueim.model.responses

class StatisticResponse(successInput: Boolean) {

    var success: Boolean = successInput

    fun isSuccess(): Boolean {
        return success
    }

}
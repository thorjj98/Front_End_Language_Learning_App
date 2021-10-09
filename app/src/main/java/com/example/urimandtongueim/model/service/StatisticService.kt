package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.LanguageRequest
import com.example.urimandtongueim.model.requests.StatisticRequest
import com.example.urimandtongueim.model.responses.LanguageResponse
import com.example.urimandtongueim.model.responses.StatisticResponse

class StatisticService {

    var server = ServerFacade()


    fun getStatistics(request: StatisticRequest): StatisticResponse {
        var response: StatisticResponse = server.getStatsitics(request)
        return response
    }
}
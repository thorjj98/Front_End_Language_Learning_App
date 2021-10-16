package com.example.urimandtongueim.model.service

import com.example.urimandtongueim.model.ServerFacade
import com.example.urimandtongueim.model.requests.FileRequest
import com.example.urimandtongueim.model.responses.FileResponse

class FileService {
    var server = ServerFacade()

    fun getJson(request: FileRequest): FileResponse? {
        var response: FileResponse? = server.getJson(request)
        return response
    }
}
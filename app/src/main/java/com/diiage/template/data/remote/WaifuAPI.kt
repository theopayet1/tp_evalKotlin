package com.diiage.template.data.remote

import com.diiage.template.data.dto.WaifuSearchResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WaifuApi(
    private val client: HttpClient
) {
    suspend fun searchPortraitWaifus(limit: Int): WaifuSearchResponseDto {
        return client.get("search") {
            parameter("included_tags", "waifu")
            parameter("is_nsfw", "false")
            parameter("orientation", "PORTRAIT")
            parameter("limit", limit)
        }.body()
    }
}

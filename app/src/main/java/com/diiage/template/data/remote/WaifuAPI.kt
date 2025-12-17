package com.diiage.template.data.remote

import com.diiage.template.data.dto.WaifuSearchResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

//elle encapsule les appels HTTP vers waifu.im
//
//elle centralise les paramètres API (tags, nsfw, orientation, limit)
class WaifuApi(
    private val client: HttpClient // le HttpClient est injecté et crée par Koin
) {
    //pour laisser la possibiliter d'evoluer
    suspend fun searchPortraitWaifus(limit: Int): WaifuSearchResponseDto { //l’appel réseau est asynchrone
        return client.get("search") {
            parameter("included_tags", "waifu")
            parameter("is_nsfw", "false")
            parameter("orientation", "PORTRAIT")
            parameter("limit", limit)
        }.body()
    }
}

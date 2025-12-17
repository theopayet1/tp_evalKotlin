package com.diiage.template.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
//configuration globale du client HTTP
//Ktor est le client HTTP
//CIO est l’engine réseau utilisé
object WaifuHttpClient {

    fun create(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {//ça permet de transformer automatiquement le JSON en objets Kotlin
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true//plus permisif
                    }
                )
            }

            install(DefaultRequest) {
                url("https://api.waifu.im/")
                header(HttpHeaders.Accept, ContentType.Application.Json)
                contentType(ContentType.Application.Json)
            }

            expectSuccess = true
        }
    }
}

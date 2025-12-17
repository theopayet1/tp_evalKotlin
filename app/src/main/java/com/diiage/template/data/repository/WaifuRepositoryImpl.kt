package com.diiage.template.data.repository

import com.diiage.template.data.dto.toDomain
import com.diiage.template.data.remote.WaifuApi
import com.diiage.template.domain.model.WaifuImage
import com.diiage.template.domain.repository.WaifuRepository

class WaifuRepositoryImpl(
    private val api: WaifuApi
) : WaifuRepository {

    override suspend fun getPortraitWaifus(limit: Int): List<WaifuImage> {
        val response = api.searchPortraitWaifus(limit = limit)
        return response.images.map { it.toDomain() }
    }
}

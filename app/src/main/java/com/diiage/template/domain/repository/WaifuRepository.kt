package com.diiage.template.domain.repository

import com.diiage.template.domain.model.WaifuImage

interface WaifuRepository {
    suspend fun getPortraitWaifus(limit: Int): List<WaifuImage>
}
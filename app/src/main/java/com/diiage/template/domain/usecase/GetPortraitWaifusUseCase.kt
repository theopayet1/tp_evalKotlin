package com.diiage.template.domain.usecase

import com.diiage.template.domain.model.WaifuImage
import com.diiage.template.domain.repository.WaifuRepository

class GetPortraitWaifusUseCase(
    private val repository: WaifuRepository
) {
    suspend operator fun invoke(limit: Int = 10): List<WaifuImage> {
        return repository.getPortraitWaifus(limit = limit)
    }
}
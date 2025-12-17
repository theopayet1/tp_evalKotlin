package com.diiage.template.domain.usecase

import com.diiage.template.domain.model.WaifuImage
import com.diiage.template.domain.repository.WaifuRepository
/**
 * récupére une liste de waifus en format portrait.
 *
 *
 *
 * @param limit Nombre maximum de waifus à récupérer (10 par défaut).
 * @return Liste de [WaifuImage] prête à être affichée par la couche UI.
 */
class GetPortraitWaifusUseCase(
    private val repository: WaifuRepository
) {
    suspend operator fun invoke(limit: Int = 10): List<WaifuImage> {
        return repository.getPortraitWaifus(limit = limit)
    }
}
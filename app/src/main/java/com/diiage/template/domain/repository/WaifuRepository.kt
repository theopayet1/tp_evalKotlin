package com.diiage.template.domain.repository

import com.diiage.template.domain.model.WaifuImage

/**
 * Contrat du repository Waifu Uwu
 *
 * Cette interface définit les opérations métier liées aux waifus,
 *
 *
 * @see com.diiage.template.data.repository.WaifuRepositoryImpl
 */
interface WaifuRepository {

    /**
     * Récupère une liste de waifus au format portrait.
     *
     * La fonction est marquée `suspend` car l’implémentation peut
     * effectuer un appel réseau asynchrone, sans bloquer le thread UI.
     *
     * @param limit Nombre  waifus
     * @return Liste de [WaifuImage]
     */
    suspend fun getPortraitWaifus(limit: Int): List<WaifuImage>
}
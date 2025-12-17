package com.diiage.template.data.dto

import com.diiage.template.domain.model.WaifuImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/*
 serializable pour désérialisation automatique JSON → Kotlin
 */
@Serializable
data class WaifuSearchResponseDto(
    @SerialName("images")
    val images: List<WaifuImageDto>
)

@Serializable
data class WaifuImageDto(
    /*l’API renvoie du snake_case (image_id, preview_url)
    *Kotlin utilise du camelCase
    *@SerialName fait le lien sans casser les conventions Kotlin
     */
    @SerialName("image_id")
    val imageId: Long,

    @SerialName("url")
    val url: String,

    @SerialName("preview_url")
    val previewUrl: String?,

    @SerialName("width")
    val width: Int,

    @SerialName("height")
    val height: Int
)

/**
 * Mapping DTO -> Domain.
 * Le modèle domain est indépendant du format API.
 */
//Si le projet devenait plus complexe, je pourrais extraire un mapper dédié.
fun WaifuImageDto.toDomain(): WaifuImage {
    return WaifuImage(
        id = imageId,
        url = url,
        previewUrl = previewUrl,
        width = width,
        height = height
    )
}

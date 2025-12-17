// app/src/main/java/com/diiage/template/data/dto/WaifuDtos.kt
package com.diiage.template.data.dto

import com.diiage.template.domain.model.WaifuImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaifuSearchResponseDto(
    @SerialName("images")
    val images: List<WaifuImageDto>
)

@Serializable
data class WaifuImageDto(

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

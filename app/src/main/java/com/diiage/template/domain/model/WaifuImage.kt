package com.diiage.template.domain.model

data class WaifuImage(
    val id: Long, // long par securité
    val url: String,
    val previewUrl: String?,
    val width: Int,
    val height: Int
)
package com.zenkun.domain.model

data class PowerUpModel(
    val title: String,
    val description: String,
    val longDescription: String,
    val storeUrl: String,
    val imageUrl: String,
    val isConnected: Boolean
)
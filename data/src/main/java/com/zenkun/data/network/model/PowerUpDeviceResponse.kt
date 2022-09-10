package com.zenkun.data.network.model

data class PowerUpDeviceResponse(
    val title: String,
    val description: String,
    val longDescription: String,
    val storeUrl: String,
    val imageUrl: String,
    val connected: Boolean
)
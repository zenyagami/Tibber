package com.zenkun.tibber.ui.compose

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.zenkun.domain.model.PowerUpModel

fun getMockedPowerUpList(): List<PowerUpModel> {
    return listOf(
        PowerUpModel(
            title = "Tesla",
            description = "Smart chare your Tesla",
            imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/tesla.png",
            longDescription = LoremIpsum().values.take(100).toString(),
            storeUrl = "",
            isConnected = false
        ),
        PowerUpModel(
            title = "Easee",
            description = "mart charge your electric vehicle",
            imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/easee.png",
            longDescription = LoremIpsum().values.take(100).toString(),
            storeUrl = "",
            isConnected = true
        ),
        PowerUpModel(
            title = "Nissan",
            description = "Smart charge your  based on the energy price",
            imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/nissan.png",
            longDescription = LoremIpsum().values.take(100).toString(),
            storeUrl = "",
            isConnected = false
        ),
        PowerUpModel(
            title = "Solar Edge",
            description = "Connected solar inverters",
            imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/solar_edge.png",
            longDescription = LoremIpsum().values.take(100).toString(),
            storeUrl = "",
            isConnected = false
        ))
}
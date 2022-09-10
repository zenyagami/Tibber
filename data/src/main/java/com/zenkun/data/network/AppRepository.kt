package com.zenkun.data.network

import com.zenkun.data.network.model.PowerUpDeviceResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getDevices(): Flow<List<PowerUpDeviceResponse>?>
}
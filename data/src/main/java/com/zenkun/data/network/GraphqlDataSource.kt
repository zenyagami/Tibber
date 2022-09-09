package com.zenkun.data.network

import com.zenkun.data.PowerUpDevicesQuery
import kotlinx.coroutines.flow.Flow

interface GraphqlDataSource {
    fun queryDevices(): Flow<List<PowerUpDevicesQuery.AssignmentDatum>?>
}
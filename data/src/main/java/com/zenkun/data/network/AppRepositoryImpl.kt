package com.zenkun.data.network

import com.zenkun.data.network.model.PowerUpDeviceResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val graphqlDataSource: GraphqlDataSource
) : AppRepository {

    override fun getDevices(): Flow<List<PowerUpDeviceResponse>?> {
        return graphqlDataSource.queryDevices()
    }
}
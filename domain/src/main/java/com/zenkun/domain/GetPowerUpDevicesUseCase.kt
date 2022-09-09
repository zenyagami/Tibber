package com.zenkun.domain

import com.zenkun.data.network.GraphqlDataSource
import com.zenkun.domain.model.PowerUpModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPowerUpDevicesUseCase @Inject constructor(
    private val graphqlDataSource: GraphqlDataSource
) {
    fun execute(): Flow<List<PowerUpModel>> {
        return graphqlDataSource.queryDevices()
            .map {
                it?.map { item ->
                    PowerUpModel(
                        title = item.title,
                        description = item.description,
                        longDescription = item.longDescription,
                        storeUrl = item.storeUrl,
                        imageUrl = item.imageUrl,
                        isConnected = item.connected
                    )
                } ?: emptyList()

            }
    }
}
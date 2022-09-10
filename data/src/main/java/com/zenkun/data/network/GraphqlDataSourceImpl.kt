package com.zenkun.data.network

import com.apollographql.apollo3.ApolloClient
import com.zenkun.data.PowerUpDevicesQuery
import com.zenkun.data.network.model.PowerUpDeviceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GraphqlDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : GraphqlDataSource {

    override fun queryDevices(): Flow<List<PowerUpDeviceResponse>?> {
        return flow {
            emit(apolloClient.query(PowerUpDevicesQuery()).execute())
        }.map {
            it.data?.assignmentData?.map { item ->
                PowerUpDeviceResponse(
                    title = item.title,
                    description = item.description,
                    longDescription = item.longDescription,
                    storeUrl = item.storeUrl,
                    imageUrl = item.imageUrl,
                    connected = item.connected
                )
            }
        }
    }
}
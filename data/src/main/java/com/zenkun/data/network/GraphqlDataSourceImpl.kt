package com.zenkun.data.network

import com.apollographql.apollo3.ApolloClient
import com.zenkun.data.PowerUpDevicesQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GraphqlDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : GraphqlDataSource {

    override fun queryDevices(): Flow<List<PowerUpDevicesQuery.AssignmentDatum>?> {
        return flow {
            emit(apolloClient.query(PowerUpDevicesQuery()).execute())
        }.map {
            it.data?.assignmentData
        }

    }
}
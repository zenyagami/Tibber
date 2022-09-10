@file:OptIn(ExperimentalCoroutinesApi::class)

package com.zenkun.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.zenkun.data.network.AppRepository
import com.zenkun.data.network.AppRepositoryImpl
import com.zenkun.data.network.GraphqlDataSource
import com.zenkun.data.network.model.PowerUpDeviceResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.random.Random

class AppRepositoryTest {
    private val graphqlDataSource: GraphqlDataSource = mock()
    private val appRepository: AppRepository = AppRepositoryImpl(graphqlDataSource)

    @Test
    fun `should return the correct mapping`() = runTest {
        // Given
        val items = getMockedPowerUpResponse()
        whenever(graphqlDataSource.queryDevices()).thenReturn(flowOf(items))

        appRepository.getDevices().test {
            val emission = awaitItem()
            assertThat(emission).containsExactlyElementsIn(items)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun getMockedPowerUpResponse(): List<PowerUpDeviceResponse> {
        return mutableListOf<PowerUpDeviceResponse>().apply {
            for (i in 5 downTo 0) {
                add(
                    PowerUpDeviceResponse(
                        title = UUID.randomUUID().toString(),
                        description = UUID.randomUUID().toString(),
                        longDescription = UUID.randomUUID().toString(),
                        storeUrl = UUID.randomUUID().toString(),
                        imageUrl = UUID.randomUUID().toString(),
                        connected = Random.nextBoolean()
                    )
                )
            }
        }
    }

}
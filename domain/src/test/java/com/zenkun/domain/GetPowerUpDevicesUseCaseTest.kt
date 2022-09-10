package com.zenkun.domain

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.zenkun.data.network.AppRepository
import com.zenkun.data.network.model.PowerUpDeviceResponse
import com.zenkun.domain.model.PowerUpModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.random.Random

class GetPowerUpDevicesUseCaseTest {
    private val appRepository: AppRepository = mock()
    private val getPowerUpDevicesUseCase = GetPowerUpDevicesUseCase(appRepository)

    @Test
    fun `should return the correct mapping`() = runTest {
        // Given
        val items = getMockedPowerUpResponse()
        val expected = items.toPowerUpModelList()
        whenever(appRepository.getDevices()).thenReturn(flowOf(items))

        getPowerUpDevicesUseCase.execute().test {
            val emission = awaitItem()
            val total = emission.connectedDevices + emission.disconnectedDevices
            Truth.assertThat(total).containsExactlyElementsIn(expected)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun List<PowerUpDeviceResponse>.toPowerUpModelList(): List<PowerUpModel> {
        return this.map { item ->
            PowerUpModel(
                title = item.title,
                description = item.description,
                longDescription = item.longDescription,
                storeUrl = item.storeUrl,
                imageUrl = item.imageUrl,
                isConnected = item.connected
            )
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
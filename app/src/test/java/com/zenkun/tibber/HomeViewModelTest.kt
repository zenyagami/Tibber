package com.zenkun.tibber

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.zenkun.domain.GetPowerUpDevicesUseCase
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.tibber.common.HomeViewModel
import com.zenkun.tibber.common.model.Lce
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.random.Random

class HomeViewModelTest {
    private val getPowerUpDevicesUseCase: GetPowerUpDevicesUseCase = mock()
    private lateinit var viewModel: HomeViewModel
    private val items = getMockedDevices()

    @Before
    fun setup() {
        whenever(getPowerUpDevicesUseCase.execute()).thenReturn(flowOf(items))
        viewModel = HomeViewModel(getPowerUpDevicesUseCase)
    }

    @Test
    fun `should displayLoading`() = runTest {
        // Given

        viewModel.getList.test {
            val emission = awaitItem()
            assertThat(emission).isInstanceOf(Lce.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should display the success items`() = runTest {
        // Given

        viewModel.getList.test {
            awaitItem() //ignore loading
            val emission = awaitItem()
            assertThat(emission).isInstanceOf(Lce.Success::class.java)
            val items = (emission as Lce.Success).data
            assertThat(items).containsExactlyElementsIn(items)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun getMockedDevices(): MutableList<PowerUpModel> {
        return mutableListOf<PowerUpModel>().apply {
            for (i in 5 downTo 0) {
                add(
                    PowerUpModel(
                        title = UUID.randomUUID().toString(),
                        description = UUID.randomUUID().toString(),
                        longDescription = UUID.randomUUID().toString(),
                        storeUrl = UUID.randomUUID().toString(),
                        imageUrl = UUID.randomUUID().toString(),
                        isConnected = Random.nextBoolean()
                    )
                )
            }
        }
    }
}
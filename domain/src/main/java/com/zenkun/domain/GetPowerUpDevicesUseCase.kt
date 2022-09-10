package com.zenkun.domain

import com.zenkun.data.network.AppRepository
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.domain.model.PowerUpViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPowerUpDevicesUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    fun execute(): Flow<PowerUpViewState> {
        return appRepository.getDevices()
            .map { devices ->
                val mappedItems = devices?.map { item ->
                    PowerUpModel(
                        title = item.title,
                        description = item.description,
                        longDescription = item.longDescription,
                        storeUrl = item.storeUrl,
                        imageUrl = item.imageUrl,
                        isConnected = item.connected
                    )
                } ?: emptyList()

                /*
                 this is a workaround, for small data set is ok to filter
                 but for larger items a separate query/pagination might be needed
                * */
                PowerUpViewState(
                    connectedDevices = mappedItems.filter { it.isConnected },
                    disconnectedDevices = mappedItems.filter { it.isConnected.not() }
                )
            }
    }
}
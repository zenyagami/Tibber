package com.zenkun.tibber.common

import androidx.lifecycle.ViewModel
import com.zenkun.domain.GetPowerUpDevicesUseCase
import com.zenkun.tibber.common.extensions.remoteLce
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPowerUpDevicesUseCase: GetPowerUpDevicesUseCase
) : ViewModel() {

    val getList = getPowerUpDevicesUseCase.execute()
        .remoteLce()
}
package com.zenkun.domain.model

data class PowerUpViewState(
    val connectedDevices: List<PowerUpModel>,
    val disconnectedDevices: List<PowerUpModel>,
)
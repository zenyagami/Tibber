package com.zenkun.tibber.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenkun.tibber.common.model.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PowerUpDetailsViewModel @Inject constructor() : ViewModel() {
    private val _viewState = mutableStateOf<Lce<Boolean>?>(null)
    val viewState: MutableState<Lce<Boolean>?> = _viewState

    // emulate connect/disconnect
    fun changeConnectState(isConnected: Boolean) {
        viewModelScope.launch {
            _viewState.value = Lce.Loading
            delay(DELAY_IN_MILLIS)
            _viewState.value = Lce.Success(isConnected)
        }
    }

    private companion object {
        const val DELAY_IN_MILLIS = 1500L
    }
}
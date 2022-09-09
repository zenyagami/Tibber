package com.zenkun.tibber.common.model

interface Event

sealed class Lce<out Data> : Event {
    object Loading : Lce<Nothing>()
    data class Failure(val error: Throwable) : Lce<Nothing>()
    data class Success<Data>(val data: Data) : Lce<Data>()
}

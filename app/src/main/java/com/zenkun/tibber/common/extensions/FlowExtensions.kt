package com.zenkun.tibber.common.extensions

import com.zenkun.tibber.common.model.Lce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

fun <T> Flow<T>.remoteLce(): Flow<Lce<T>> = this
    .map<T, Lce<T>> { Lce.Success(it) }
    .onStart { emit(Lce.Loading) }
    .catch { error ->
        Timber.e(error)
        emit(Lce.Failure(error))
    }

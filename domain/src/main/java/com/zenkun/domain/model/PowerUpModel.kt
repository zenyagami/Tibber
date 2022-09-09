package com.zenkun.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PowerUpModel(
    val title: String,
    val description: String,
    val longDescription: String,
    val storeUrl: String,
    val imageUrl: String,
    val isConnected: Boolean
) : Parcelable

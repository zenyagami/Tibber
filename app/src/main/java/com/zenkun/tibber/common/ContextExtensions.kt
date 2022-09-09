package com.zenkun.tibber.common

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.openCustomTab(url: String) {
    kotlin.runCatching {
        CustomTabsIntent.Builder()
            .build()
            .launchUrl(this, Uri.parse(url))
    }
}
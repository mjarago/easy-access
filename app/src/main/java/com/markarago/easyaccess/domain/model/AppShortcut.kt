package com.markarago.easyaccess.domain.model

import android.graphics.drawable.Drawable

data class AppShortcut(
    val displayName: String,
    val packageName: String,
    val icon: Drawable?
)
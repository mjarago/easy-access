package com.markarago.easyaccess.ui.model

import android.graphics.drawable.Drawable

data class Shortcut(
    val id: String,
    val displayName: String,
    val value: String,
    val icon: Drawable?,
    val shortcutType: ShortcutType = ShortcutType.App
)
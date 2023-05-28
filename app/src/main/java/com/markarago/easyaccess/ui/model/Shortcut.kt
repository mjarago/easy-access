package com.markarago.easyaccess.ui.model

import android.graphics.drawable.Drawable

data class Shortcut(
    val id: String,
    val displayName: String,
    val value: String,
    val icon: Drawable?,
    val shortcutType: ShortcutType = ShortcutType.App
)

sealed interface MainScreenViewState {

    object LoadShortcuts: MainScreenViewState

    data class Error(val error: Throwable? = null): MainScreenViewState

    data class Success(val data: List<Shortcut>): MainScreenViewState

}
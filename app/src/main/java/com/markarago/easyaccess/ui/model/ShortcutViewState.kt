package com.markarago.easyaccess.ui.model

import com.markarago.easyaccess.domain.model.AppShortcut

sealed interface ShortcutViewState {

    object Loading : ShortcutViewState

    data class Error(val error: Throwable? = null) : ShortcutViewState

    data class AppData(val data: List<AppShortcut>) : ShortcutViewState

//    data class Contact(val data: List<Shortcut>) : ShortcutViewState
}
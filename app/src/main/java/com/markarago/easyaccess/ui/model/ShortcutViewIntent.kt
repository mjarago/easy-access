package com.markarago.easyaccess.ui.model

sealed interface ShortcutViewIntent {
    object LoadApps: ShortcutViewIntent

    object LoadContacts: ShortcutViewIntent
}

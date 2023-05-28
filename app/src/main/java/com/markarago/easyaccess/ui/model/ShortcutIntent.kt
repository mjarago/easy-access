package com.markarago.easyaccess.ui.model

sealed class ShortcutIntent {

    object Load: ShortcutIntent()

    data class AddShortcut(val actionType: ShortcutType): ShortcutIntent()
}
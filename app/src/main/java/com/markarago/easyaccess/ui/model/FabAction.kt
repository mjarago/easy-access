package com.markarago.easyaccess.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FabAction(
    val name: String,
    val contentDescription: String? = null,
    val icon: ImageVector,
    val type: ShortcutType,
)

enum class ShortcutType {
    App,
    Phone,
    Email,
    Website
}

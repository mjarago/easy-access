package com.markarago.easyaccess.domain.transformer

import com.markarago.easyaccess.data.model.AppDTO
import com.markarago.easyaccess.domain.model.AppShortcut

open class ShortcutTransformer {
    open fun transform(appDTO: AppDTO): AppShortcut {
        return AppShortcut(
            displayName = appDTO.displayName,
            packageName = appDTO.packageName,
            icon = appDTO.icon
        )
    }
}
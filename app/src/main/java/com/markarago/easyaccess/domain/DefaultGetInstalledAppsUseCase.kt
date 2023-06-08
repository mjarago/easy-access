package com.markarago.easyaccess.domain

import com.markarago.easyaccess.data.repository.AppsRepository
import com.markarago.easyaccess.domain.model.AppShortcut
import com.markarago.easyaccess.domain.transformer.ShortcutTransformer

class DefaultGetInstalledAppsUseCase(
    private val appsRepository: AppsRepository,
    private val transformer: ShortcutTransformer
): GetInstalledAppsUseCase {

    override fun getInstalledApps(): List<AppShortcut> {
        return appsRepository.getInstalledApps().map {
            transformer.transform(it)
        }
    }
}
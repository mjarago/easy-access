package com.markarago.easyaccess.domain

import com.markarago.easyaccess.domain.model.AppShortcut

interface GetInstalledAppsUseCase {

    fun getInstalledApps(): List<AppShortcut>
}
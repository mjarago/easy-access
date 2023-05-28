package com.markarago.easyaccess.data.repository

import com.markarago.easyaccess.data.model.AppDTO
import com.markarago.easyaccess.data.source.AppsDataSource

class DefaultAppsRepository(
    val dataSource: AppsDataSource
): AppsRepository {
    override fun getInstalledApps(): List<AppDTO> {
        return dataSource.getInstalledApps()
    }
}
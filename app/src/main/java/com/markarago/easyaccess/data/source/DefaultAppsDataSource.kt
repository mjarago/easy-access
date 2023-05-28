package com.markarago.easyaccess.data.source

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.markarago.easyaccess.data.model.AppDTO

class DefaultAppsDataSource(
    private val packageManager: PackageManager
): AppsDataSource {
    override fun getInstalledApps(): List<AppDTO> {
        @Suppress( "DEPRECATION")
        val installedApplications: List<ApplicationInfo> = packageManager.getInstalledApplications(
            PackageManager.GET_META_DATA)
        val installedApps = mutableListOf<AppDTO>()
        for (appInfo: ApplicationInfo in installedApplications) {
            val displayName = packageManager.getApplicationLabel(appInfo).toString()
            val icon = packageManager.getApplicationIcon(appInfo)
            val packageName = appInfo.packageName
            if (!displayName.startsWith("com.")
                && appInfo.flags != ApplicationInfo.FLAG_SYSTEM) {
                val appShortcut = AppDTO(
                    displayName = displayName,
                    icon = icon,
                    packageName = packageName,
                )
                installedApps.add(appShortcut)
            }
        }
        return installedApps
    }
}
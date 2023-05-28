package com.markarago.easyaccess.data.source

import com.markarago.easyaccess.data.model.AppDTO

interface AppsDataSource {
    fun getInstalledApps(): List<AppDTO>
}
package com.markarago.easyaccess.data.repository

import com.markarago.easyaccess.data.model.AppDTO

interface AppsRepository {
    fun getInstalledApps(): List<AppDTO>
}
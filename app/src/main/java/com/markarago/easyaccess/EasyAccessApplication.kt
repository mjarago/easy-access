package com.markarago.easyaccess

import android.app.Application
import com.markarago.easyaccess.data.repository.DefaultAppsRepository
import com.markarago.easyaccess.data.source.DefaultAppsDataSource
import com.markarago.easyaccess.domain.DefaultGetInstalledAppsUseCase
import com.markarago.easyaccess.domain.transformer.ShortcutTransformer

class EasyAccessApplication: Application() {

    private val localAppSource by lazy {
        DefaultAppsDataSource(packageManager)
    }

    private val repository by lazy {
        DefaultAppsRepository(localAppSource)
    }

    val getInstalledAppsUseCase by lazy {
        DefaultGetInstalledAppsUseCase(
            repository,
            ShortcutTransformer()
        )
    }

}
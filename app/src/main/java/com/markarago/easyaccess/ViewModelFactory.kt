package com.markarago.easyaccess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.markarago.easyaccess.ui.model.MainScreenViewModel
import com.markarago.easyaccess.ui.model.ShortcutViewModel

@Suppress("UNCHECKED_CAST")
val EasyAccessViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        with(modelClass) {
            val application = checkNotNull(extras[APPLICATION_KEY]) as EasyAccessApplication
            val getInstalledAppsUseCase = application.getInstalledAppsUseCase
            when {
                isAssignableFrom(MainScreenViewModel::class.java) ->
                    MainScreenViewModel()
                isAssignableFrom(ShortcutViewModel::class.java) ->
                    ShortcutViewModel(getInstalledAppsUseCase)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
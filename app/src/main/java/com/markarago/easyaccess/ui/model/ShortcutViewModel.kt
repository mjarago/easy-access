package com.markarago.easyaccess.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markarago.easyaccess.domain.GetInstalledAppsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ShortcutViewModel(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase
): ViewModel() {
    private val _state: MutableStateFlow<ShortcutViewState> = MutableStateFlow(ShortcutViewState.Loading)

    val state = _state.asStateFlow()

    private val intents: MutableStateFlow<ShortcutViewIntent?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            intents
                .filterNotNull()
                .collect { intent ->
                    when (intent) {
                        is ShortcutViewIntent.LoadApps -> loadApps()
                        is ShortcutViewIntent.LoadContacts -> loadContacts()
                    }
                }
        }
    }

    fun onIntent(shortcutViewIntent: ShortcutViewIntent) {
        viewModelScope.launch {
            intents.value = shortcutViewIntent
        }
    }

    private fun loadContacts() {
        TODO("Not yet implemented")
    }

    private fun loadApps() {
        _state.value = ShortcutViewState.Loading
        viewModelScope.launch {
            delay(500L)
            val installedApps = getInstalledAppsUseCase.getInstalledApps()
            _state.value = ShortcutViewState.AppData(installedApps)
        }
    }
}
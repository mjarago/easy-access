package com.markarago.easyaccess.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {

    private var _state: MutableStateFlow<MainScreenViewState> = MutableStateFlow(MainScreenViewState.LoadShortcuts)
    val state: StateFlow<MainScreenViewState> = _state.asStateFlow()

    private val intents: MutableStateFlow<MainScreenIntent> = MutableStateFlow(MainScreenIntent.Load)

    init {
        viewModelScope.launch {
            intents
                .filterNotNull()
                .collect { intent ->
                    when (intent) {
                        is MainScreenIntent.Load -> load()
                    }
                }
        }
    }

    private fun load() {
        // TODO get actual data
        viewModelScope.launch {
            delay(500L)
            _state.value = MainScreenViewState.Success(
                fakeList()
            )
        }
    }

    private fun fakeList(): List<Shortcut> {
        return listOf(
            Shortcut(
                id = "1",
                displayName = "Test App",
                value = "com.markarago.easyaccess",
                icon = null,
                shortcutType = ShortcutType.App
            ),
            Shortcut(
                id = "1",
                displayName = "Phone Number",
                value = "+1 (236) 330 2950",
                icon = null,
                shortcutType = ShortcutType.Phone
            ),
            Shortcut(
                id = "1",
                displayName = "Email",
                value = "markjosepharago@gmail.com",
                icon = null,
                shortcutType = ShortcutType.Email
            ),
            Shortcut(
                id = "1",
                displayName = "Website",
                value = "https://google.ca",
                icon = null,
                shortcutType = ShortcutType.Website
            ),
            Shortcut(
                id = "1",
                displayName = "Test App",
                value = "com.markarago.easyaccess",
                icon = null,
                shortcutType = ShortcutType.App
            ),
            Shortcut(
                id = "1",
                displayName = "Phone Number",
                value = "+1 (236) 330 2950",
                icon = null,
                shortcutType = ShortcutType.Phone
            ),
            Shortcut(
                id = "1",
                displayName = "Email",
                value = "markjosepharago@gmail.com",
                icon = null,
                shortcutType = ShortcutType.Email
            ),
            Shortcut(
                id = "1",
                displayName = "Website",
                value = "https://google.ca",
                icon = null,
                shortcutType = ShortcutType.Website
            )
        )
    }
}
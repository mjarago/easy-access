package com.markarago.easyaccess.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markarago.easyaccess.ui.model.ShortcutType
import com.markarago.easyaccess.ui.model.ShortcutIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ShortcutViewModel: ViewModel() {


    private val intents: MutableStateFlow<ShortcutIntent> = MutableStateFlow(ShortcutIntent.Load)

    init {
        viewModelScope.launch {
            intents
                .filterNotNull()
                .collect { intent ->
                    when (intent) {
                        is ShortcutIntent.Load -> TODO()
                        is ShortcutIntent.AddShortcut -> invokeAction(intent.actionType)
                    }
                }
        }
    }

    fun onIntent(intent: ShortcutIntent) {
        viewModelScope.launch {
            intents.value = intent
        }
    }

    fun invokeAction(type: ShortcutType) {
        when (type) {
            ShortcutType.App -> TODO() // Add Application
            ShortcutType.Phone -> TODO() // Add Phone
            ShortcutType.Email -> TODO() // Add Email
            ShortcutType.Website -> TODO() // Add Url
        }
    }
}
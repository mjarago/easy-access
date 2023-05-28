package com.markarago.easyaccess.ui.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.markarago.easyaccess.ui.FabState.*
import com.markarago.easyaccess.ui.model.Action
import com.markarago.easyaccess.ui.model.ShortcutType

@Composable
fun rememberEasyAccessUiState(): EasyAccessUiState = remember {
    EasyAccessUiState()
}

class EasyAccessUiState {

    private val fabState = mutableStateOf(Collapsed)

    fun toggle() {
        fabState.value = if (fabState.value == Expanded) {
            Collapsed
        } else {
            Expanded
        }
    }

    fun isExpanded() = fabState.value == Expanded

    fun getActions(): List<Action> {
        return listOf(
            Action(
                name = "App",
                contentDescription = "Add Application",
                icon = Icons.Outlined.Face,
                type = ShortcutType.App
            ),
            Action(
                name = "Phone",
                contentDescription = "Add Phone",
                icon = Icons.Outlined.Phone,
                type = ShortcutType.Phone
            ),
            Action(
                name = "Email",
                contentDescription = "Add email contact ",
                icon = Icons.Outlined.Email,
                type = ShortcutType.Email
            ),
            Action(
                name = "Website",
                contentDescription = "Add Website URL",
                icon = Icons.Outlined.Add,
                type = ShortcutType.Website
            )
        )
    }
}
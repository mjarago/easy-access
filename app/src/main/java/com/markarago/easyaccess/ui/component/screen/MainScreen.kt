package com.markarago.easyaccess.ui.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.markarago.easyaccess.ui.component.common.ShortcutCard
import com.markarago.easyaccess.ui.component.common.SpeedDialFAB
import com.markarago.easyaccess.ui.model.MainScreenViewModel
import com.markarago.easyaccess.ui.model.MainScreenViewState
import com.markarago.easyaccess.ui.state.EasyAccessUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: EasyAccessUiState,
    mainScreenViewModel: MainScreenViewModel,
    onFabItemClick: (String) -> Unit
) {
    val viewState by mainScreenViewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        floatingActionButton = {
            SpeedDialFAB(
                isExpanded = uiState.isExpanded(),
                actions = uiState.getActions(),
                toggle = uiState::toggle,
                onItemClick = onFabItemClick
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Easy Access")
                }
            )
        }
    ) { contentPadding ->
        when (viewState) {
            is MainScreenViewState.Error -> CircularProgressIndicator()
            is MainScreenViewState.LoadShortcuts -> CircularProgressIndicator()
            is MainScreenViewState.Success -> {
                val state = viewState as MainScreenViewState.Success
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(64.dp),
                    modifier = Modifier,
                    contentPadding = contentPadding,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.data) {
                        ShortcutCard(it)
                    }
                }
            }
        }
    }
}
package com.markarago.easyaccess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.markarago.easyaccess.ui.ShortcutViewModel
import com.markarago.easyaccess.ui.component.SpeedDialFAB
import com.markarago.easyaccess.ui.state.rememberEasyAccessUiState
import com.markarago.easyaccess.ui.theme.EasyAccessTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = rememberEasyAccessUiState()
            val viewModel = ShortcutViewModel()
            EasyAccessTheme {
                Scaffold(
                    floatingActionButton = {
                        SpeedDialFAB(
                            isExpanded = uiState.isExpanded(),
                            actions = uiState.getActions(),
                            toggle = uiState::toggle,
                            onActionClick = viewModel::invokeAction
                        )
                    },
                    topBar = {
                        Text("Start App")
                    }
                ) { contentPadding ->
                    Card(modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)) {
                        Text("Content")
                    }
                }
            }
        }
    }
}
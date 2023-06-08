package com.markarago.easyaccess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.markarago.easyaccess.ui.component.screen.AppScreen
import com.markarago.easyaccess.ui.component.screen.EmailScreen
import com.markarago.easyaccess.ui.model.MainScreenViewModel
import com.markarago.easyaccess.ui.component.screen.MainScreen
import com.markarago.easyaccess.ui.component.screen.PhoneScreen
import com.markarago.easyaccess.ui.component.screen.WebsiteScreen
import com.markarago.easyaccess.ui.model.ShortcutType
import com.markarago.easyaccess.ui.model.ShortcutViewIntent
import com.markarago.easyaccess.ui.model.ShortcutViewModel
import com.markarago.easyaccess.ui.state.EasyAccessUiState
import com.markarago.easyaccess.ui.state.rememberEasyAccessUiState
import com.markarago.easyaccess.ui.theme.EasyAccessTheme

class MainActivity : ComponentActivity() {

    private val mainScreenViewModel by viewModels<MainScreenViewModel> { EasyAccessViewModelFactory }

    private val shortcutViewModel by viewModels<ShortcutViewModel> { EasyAccessViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = rememberEasyAccessUiState()
            val navController = rememberNavController()
            EasyAccessTheme {
                NavGraph(
                    uiState = uiState,
                    mainScreenViewModel = mainScreenViewModel,
                    shortcutViewModel = shortcutViewModel,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun NavGraph(
        uiState: EasyAccessUiState,
        mainScreenViewModel: MainScreenViewModel,
        shortcutViewModel: ShortcutViewModel,
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = "main"
        ) {
            composable("main") {
                MainScreen(
                    uiState = uiState,
                    mainScreenViewModel = mainScreenViewModel,
                    onFabItemClick = navController::navigate
                )
            }
            composable(ShortcutType.App.name) {
                onShortcutIntent(ShortcutViewIntent.LoadApps)
                AppScreen(
                    shortcutViewModel = shortcutViewModel
                )
            }
            composable(ShortcutType.Email.name) {
                EmailScreen()
            }
            composable(ShortcutType.Phone.name) {
                PhoneScreen()
            }
            composable(ShortcutType.Website.name) {
                WebsiteScreen()
            }

        }
    }

    fun onShortcutIntent(intent: ShortcutViewIntent) {
        shortcutViewModel.onIntent(intent)
    }
}
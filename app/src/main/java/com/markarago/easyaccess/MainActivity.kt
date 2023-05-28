package com.markarago.easyaccess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.markarago.easyaccess.ui.state.EasyAccessUiState
import com.markarago.easyaccess.ui.state.rememberEasyAccessUiState
import com.markarago.easyaccess.ui.theme.EasyAccessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = rememberEasyAccessUiState()
            val mainScreenViewModel = MainScreenViewModel()
            val navController = rememberNavController()
            EasyAccessTheme {
                NavGraph(
                    uiState = uiState,
                    mainScreenViewModel = mainScreenViewModel,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun NavGraph(
        uiState: EasyAccessUiState,
        mainScreenViewModel: MainScreenViewModel,
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
                AppScreen()
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
}
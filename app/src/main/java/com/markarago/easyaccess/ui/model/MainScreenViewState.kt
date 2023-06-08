package com.markarago.easyaccess.ui.model

sealed interface MainScreenViewState {
    object LoadShortcuts: MainScreenViewState

    data class Error(val error: Throwable? = null): MainScreenViewState

    data class Success(val data: List<Shortcut>): MainScreenViewState
}
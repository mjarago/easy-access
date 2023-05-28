package com.markarago.easyaccess.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.markarago.easyaccess.ui.model.Action
import com.markarago.easyaccess.ui.model.ShortcutType

@Composable
fun SpeedDialFAB(
    isExpanded: Boolean,
    actions: List<Action>,
    toggle: () -> Unit,
    onActionClick: (ShortcutType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        actions.forEachIndexed { index, action ->
            val animationInSpecIntOffset: FiniteAnimationSpec<IntOffset> =
                tween(delayMillis = 20 * (5 - index))
            val animationInSpecFloat: FiniteAnimationSpec<Float> =
                tween(delayMillis = 20 * (5 - index))
            val animationOutSpecIntOffset: FiniteAnimationSpec<IntOffset> =
                tween(delayMillis = 20 * index)
            val animationOutSpecFloat: FiniteAnimationSpec<Float> = tween(delayMillis = 20 * index)

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationInSpecFloat) + slideInVertically(animationInSpecIntOffset) { it / 2 },
                exit = fadeOut(animationOutSpecFloat) + slideOutVertically(animationOutSpecIntOffset) { it / 2 }
            ) {
                FloatingActionButton(
                    modifier = Modifier.size(40.dp),
                    elevation = FloatingActionButtonDefaults.elevation(
                        0.dp,
                        0.dp,
                        0.dp,
                        0.dp
                    ),
                    onClick = { onActionClick(action.type) }
                ) {
                    Icon(
                        action.icon,
                        contentDescription = action.contentDescription
                    )
                }
            }
        }
        FloatingActionButton(onClick = { toggle() }, shape = CircleShape) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add Shortcut"
            )
        }
    }
}
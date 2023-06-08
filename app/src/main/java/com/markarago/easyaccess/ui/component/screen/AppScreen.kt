package com.markarago.easyaccess.ui.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.markarago.easyaccess.R
import com.markarago.easyaccess.domain.model.AppShortcut
import com.markarago.easyaccess.ui.model.ShortcutViewIntent
import com.markarago.easyaccess.ui.model.ShortcutViewModel
import com.markarago.easyaccess.ui.model.ShortcutViewState


@Composable
fun AppScreen(
    shortcutViewModel: ShortcutViewModel
) {
    val state = shortcutViewModel.state.collectAsStateWithLifecycle()


    when (state.value) {
        is ShortcutViewState.Loading -> {
            Box(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator()
            }
        }

        is ShortcutViewState.AppData -> {
            val currentState = state.value as ShortcutViewState.AppData
            ApplicationList(data = currentState.data)
        }

        else -> {}
    }
}

@Composable
fun ApplicationList(
    data: List<AppShortcut>,
    modifier: Modifier = Modifier
) {


    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) { appShortcut ->
            // Image
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(appShortcut.icon)
                    .crossfade(true)
                    .build()
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Row {
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier.size(48.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = appShortcut.displayName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = appShortcut.packageName, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ApplicationListPreview() {

}
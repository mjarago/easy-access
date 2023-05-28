package com.markarago.easyaccess.ui.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.markarago.easyaccess.R
import com.markarago.easyaccess.ui.model.Shortcut
import com.markarago.easyaccess.ui.model.ShortcutType
import com.markarago.easyaccess.ui.theme.LightBlue
import com.markarago.easyaccess.ui.theme.LightBrown

@Composable
fun ShortcutCard(
    shortcut: Shortcut
) {
    val painter: AsyncImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(shortcut.icon)
            .fallback(R.drawable.icon_placeholder)
            .placeholder(R.drawable.icon_placeholder)
            .crossfade(true)
            .build()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ShortcutIcon(
            image = painter
        )
        Text(
            text = shortcut.displayName,
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontWeight = FontWeight.Light,
            lineHeight = 12.sp,
            fontSize = 10.sp
        )
    }

}

@Composable
fun ShortcutIcon(
    image: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = LightBlue,
    size: Dp = 48.dp,
    ) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun ShortcutCardPreview() {
    ShortcutCard(
        shortcut = Shortcut(
            id = "1",
            displayName = "Phone Number",
            value = "com.example.app",
            icon = null,
            shortcutType = ShortcutType.App
        )
    )
}
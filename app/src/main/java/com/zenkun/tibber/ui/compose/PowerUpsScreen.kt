@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.zenkun.tibber.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.tibber.R
import com.zenkun.tibber.common.theme.TibberTheme

@Composable
fun PowerUpsScreenContent(
    isLoading: Boolean,
    powerUpList: List<PowerUpModel>
) {
    Scaffold(topBar = {
        Column(Modifier.fillMaxWidth()) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.power_ups_view_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 32.dp)
            ) {
                stickyHeader {
                    Text(
                        text = stringResource(
                            id = R.string.available_power_up_header_label
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                items(powerUpList) { item ->
                    PowerUpItem(
                        title = item.title,
                        description = item.description,
                        imageUrl = item.imageUrl,
                        onItemClicked = {}
                    )
                }
            }
        }
    }
}

@Composable
fun PowerUpItem(
    title: String,
    description: String,
    imageUrl: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onItemClicked,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
                .padding(horizontal = 20.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .align(CenterVertically),
                tint = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.7f
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPowerUpItem() {
    TibberTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            PowerUpItem(
                title = "Tesla",
                description = "Smart chare your Tesla",
                imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/tesla.png",
                onItemClicked = {}
            )
        }

    }
}


@Composable
@Preview
private fun PreviewPowerUpsScreenContent() {
    TibberTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        PowerUpsScreenContent(
            isLoading = false,
            powerUpList = getMockedPowerUpList()
                .shuffled()
        )
    }
}
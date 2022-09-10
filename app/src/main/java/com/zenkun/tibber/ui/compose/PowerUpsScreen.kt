@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.zenkun.tibber.ui.compose

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.domain.model.PowerUpViewState
import com.zenkun.tibber.R
import com.zenkun.tibber.common.HomeViewModel
import com.zenkun.tibber.common.model.Lce
import com.zenkun.tibber.common.theme.TibberAppTheme
import com.zenkun.tibber.common.theme.TibberTheme

@Composable
fun PowerUpsScreen(
    onItemClicked: (PowerUpModel) -> Unit,
    viewModel: HomeViewModel,
) {
    val listState = viewModel.getList.collectAsState(initial = Lce.Loading).value
    var powerUpList by remember {
        mutableStateOf(
            PowerUpViewState(
                connectedDevices = emptyList(), disconnectedDevices = emptyList()
            )
        )
    }

    val isLoading = listState is Lce.Loading

    when (listState) {
        is Lce.Failure -> {
            Toast.makeText(
                LocalContext.current, R.string.general_internet_error_message, Toast.LENGTH_LONG
            ).show()
        }
        is Lce.Success -> {
            powerUpList = listState.data
        }
        Lce.Loading -> Unit
    }

    PowerUpsScreenContent(
        isLoading = isLoading, powerUpViewState = powerUpList, onItemClicked = onItemClicked
    )
}

@Composable
fun PowerUpsScreenContent(
    isLoading: Boolean,
    powerUpViewState: PowerUpViewState,
    onItemClicked: (PowerUpModel) -> Unit,
) {
    Scaffold(topBar = {
        Column(Modifier.fillMaxWidth()) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(), title = {
                    Text(
                        text = stringResource(id = R.string.power_ups_view_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
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
            ) {

                item { Spacer(modifier = Modifier.height(32.dp)) }
                powerUpViewState.connectedDevices
                    .takeIf { powerUpModels -> powerUpModels.isNotEmpty() }
                    ?.let {
                        item {
                            Text(
                                text = stringResource(
                                    id = R.string.active_power_up_header_label,
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                color = TibberAppTheme.colors.secondary,
                            )
                        }
                        items(it) {
                            PowerUpItem(
                                title = it.title,
                                description = it.description,
                                imageUrl = it.imageUrl,
                                onItemClicked = { onItemClicked(it) },
                                navIcon = Icons.Default.NavigateNext
                            )
                        }
                    }

                item {
                    Text(
                        text = stringResource(
                            id = R.string.available_power_up_header_label,
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TibberAppTheme.colors.secondary,
                    )
                }
                items(powerUpViewState.disconnectedDevices) { item ->
                    PowerUpItem(
                        title = item.title,
                        description = item.description,
                        imageUrl = item.imageUrl,
                        onItemClicked = { onItemClicked(item) },
                        navIcon = Icons.Default.NavigateNext
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
    modifier: Modifier = Modifier,
    imageSize: Dp = 70.dp,
    cardShape: Shape = CardDefaults.shape,
    onItemClicked: (() -> Unit)? = null,
    navIcon: ImageVector? = null
) {

    val clickModifier = onItemClicked?.let {
        modifier
            .clip(cardShape)
            .clickable(onClick = onItemClicked)
    } ?: modifier.clip(cardShape)

    Surface(
        shape = cardShape,
        modifier = clickModifier,
        color = TibberAppTheme.colors.cardBackgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
                .padding(horizontal = 20.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(imageSize)
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
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TibberAppTheme.colors.secondary
                )
            }
            navIcon?.let {
                Icon(
                    imageVector = it,
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
}

@Composable
@Preview(showBackground = true)
fun PreviewPowerUpItem() {
    TibberTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            PowerUpItem(title = "Tesla",
                description = "Smart chare your Tesla",
                imageUrl = "https://tibber-app-gateway.imgix.net/images/powerup/tile/tesla.png",
                onItemClicked = {})
        }

    }
}


@Composable
@Preview
private fun PreviewPowerUpsScreenContent() {
    TibberTheme(
        dynamicColor = false, darkTheme = false
    ) {
        PowerUpsScreenContent(isLoading = false,
            powerUpViewState = PowerUpViewState(
                connectedDevices = getMockedPowerUpList().shuffled().take(2),
                disconnectedDevices = getMockedPowerUpList().shuffled()
            ),
            onItemClicked = {})
    }
}
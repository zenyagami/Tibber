@file:OptIn(ExperimentalMaterial3Api::class)

package com.zenkun.tibber.ui.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.tibber.R
import com.zenkun.tibber.common.model.Lce
import com.zenkun.tibber.common.openCustomTab
import com.zenkun.tibber.common.theme.TibberAppTheme
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.PowerUpDetailsViewModel

@Composable
fun PowerUpDetailsScreen(
    powerUpModel: PowerUpModel,
    viewModel: PowerUpDetailsViewModel,
    onNavigationClicked: () -> Unit,
) {

    val onConnectStateChanged: (shouldConnect: Boolean) -> Unit = {
        viewModel.changeConnectState(it)
    }
    var item by remember { mutableStateOf(powerUpModel) }

    when (val state = viewModel.viewState.value) {
        is Lce.Success -> {
            item = item.copy(isConnected = state.data)
            if (state.data) {
                R.string.success_device_connected
            } else {
                R.string.success_device_disconnected
            }.also {
                Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
            }

        }
        Lce.Loading,
        is Lce.Failure,
        null -> Unit
    }
    val isLoading = viewModel.viewState.value is Lce.Loading
    PowerUpDetailsScreenContent(
        isLoading = isLoading,
        powerUpModel = item,
        onConnectStateChanged = onConnectStateChanged,
        onNavigationClicked = onNavigationClicked
    )
}

@Composable
private fun PowerUpDetailsScreenContent(
    isLoading: Boolean,
    powerUpModel: PowerUpModel,
    onConnectStateChanged: (shouldConnect: Boolean) -> Unit,
    onNavigationClicked: () -> Unit,
) {
    Scaffold(topBar = {
        Column(Modifier.fillMaxWidth()) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(), title = {
                    Text(
                        text = powerUpModel.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigationClicked) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(state = rememberScrollState())
        ) {
            val context = LocalContext.current
            PowerUpItem(
                title = powerUpModel.title,
                description = powerUpModel.description,
                imageUrl = powerUpModel.imageUrl,
                imageSize = 96.dp,
                cardShape = RectangleShape
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                if (powerUpModel.isConnected) {
                    // I'm using error color, is not an error but for now
                    // i can use this color for darkTheme as well
                    OutlinedTibberButton(
                        text = stringResource(id = R.string.power_up_disconnect_device_button),
                        onButtonClicked = { onConnectStateChanged(false) },
                        strokeColor = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = isLoading.not()
                    )
                } else {
                    PrimaryButton(
                        text = stringResource(
                            id = R.string.power_up_connect_device_button
                        ), onButtonClicked = { onConnectStateChanged(true) },
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = isLoading.not()
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                SecondaryButton(
                    text = stringResource(
                        id = R.string.buy_power_up_device_button
                    ), onButtonClicked = {
                        context.openCustomTab(powerUpModel.storeUrl)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = isLoading.not()
                )
                Spacer(modifier = Modifier.height(54.dp))
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = TibberAppTheme.colors.cardBackgroundColor)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.power_up_details_view_more_title_label, powerUpModel.title
                    ), style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = powerUpModel.longDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TibberAppTheme.colors.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
}

@Composable
@Preview
fun PreviewPowerUpDetailsScreenContent() {
    TibberTheme(darkTheme = false) {
        PowerUpDetailsScreenContent(
            isLoading = false,
            powerUpModel = getMockedPowerUpList().shuffled().first(),
            onConnectStateChanged = {},
            onNavigationClicked = {}
        )
    }
}
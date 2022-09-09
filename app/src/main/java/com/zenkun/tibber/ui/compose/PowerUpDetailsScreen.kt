@file:OptIn(ExperimentalMaterial3Api::class)

package com.zenkun.tibber.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.tibber.R
import com.zenkun.tibber.common.openCustomTab
import com.zenkun.tibber.common.theme.TibberAppTheme
import com.zenkun.tibber.common.theme.TibberTheme

@Composable
fun PowerUpDetailsScreenContent(powerUpModel: PowerUpModel) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(), title = {
                Text(
                    text = powerUpModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
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
                        onButtonClicked = { /*TODO*/ },
                        strokeColor = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    PrimaryButton(
                        text = stringResource(
                            id = R.string.power_up_connect_device_button
                        ), onButtonClicked = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                SecondaryButton(
                    text = stringResource(
                        id = R.string.buy_power_up_device_button
                    ), onButtonClicked = {
                        context.openCustomTab(powerUpModel.storeUrl)
                    },
                    modifier = Modifier.fillMaxWidth()
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
            }
        }

    }
}

@Composable
@Preview
fun PreviewPowerUpDetailsScreenContent() {
    TibberTheme(darkTheme = false) {
        PowerUpDetailsScreenContent(
            getMockedPowerUpList().shuffled().first()
        )
    }
}
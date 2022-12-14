package com.zenkun.tibber.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.tibber.common.theme.GrayBackground
import com.zenkun.tibber.common.theme.TibberAppTheme
import com.zenkun.tibber.common.theme.TibberTheme


@Composable
fun PrimaryButton(
    text: String,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    DefaultButton(
        text = text,
        onButtonClicked = onButtonClicked,
        modifier = modifier,
        isEnabled = isEnabled
    )
}

@Composable
fun SecondaryButton(
    text: String,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    DefaultButton(
        text = text,
        onButtonClicked = onButtonClicked,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = TibberAppTheme.colors.cardBackgroundColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        isEnabled = isEnabled
    )
}

@Composable
private fun DefaultButton(
    text: String,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    isEnabled: Boolean = true
) {
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        colors = colors,
        enabled = isEnabled

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun OutlinedTibberButton(
    text: String,
    strokeColor: Color,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onButtonClicked,
        border = BorderStroke(1.dp, strokeColor),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = strokeColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CommonButtons() {
    TibberTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .background(GrayBackground)
                .padding(16.dp)
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Connect to tibber",
                onButtonClicked = {}
            )

            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Buy at the Tibber store",
                onButtonClicked = {}
            )

            OutlinedTibberButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Disconnect from tibber",
                onButtonClicked = {},
                strokeColor = MaterialTheme.colorScheme.error
            )
        }
    }
}

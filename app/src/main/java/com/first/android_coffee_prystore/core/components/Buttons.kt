package com.first.android_coffee_prystore.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
 modifier: Modifier = Modifier,
 text: String,
 isEnabled: Boolean = true,
 onClick: () -> Unit
)
{
    Box(modifier = modifier) {
Button(
    shape = RoundedCornerShape(8.dp),
    onClick = onClick,
    enabled = isEnabled,
    modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
){
Text(
    text = text,
    style = MaterialTheme.typography.titleMedium
)
}
    }
}


@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
)
{
    Box(modifier = modifier) {
        OutlinedButton(
            border = BorderStroke(2.dp, Color.Blue),
            shape = RoundedCornerShape(8.dp),
            onClick = onClick,
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit
){
Box(modifier = modifier){
    FloatingActionButton(onClick = onClick,
        elevation = elevation) {
        icon.invoke()
    }
}
}


@Composable
fun SquareButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    isEnabled: Boolean,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    onClick: () -> Unit
)
{
    Box(modifier = modifier) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = onClick,
            enabled = isEnabled,
            elevation = elevation,
            contentPadding = PaddingValues(4.dp),
            modifier = modifier
        ){
            icon.invoke()
        }
    }
}


@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit
)
{
    Box(modifier = modifier) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = onClick,
            enabled = isEnabled,
            contentPadding = PaddingValues(4.dp),
            modifier = modifier
                .height(28.dp)
                .width(IntrinsicSize.Max)
        ){
            Text(text = text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        PrimaryButton(
            modifier = Modifier,
            text = "Primary Button enabled",
            isEnabled = true,
            onClick = {}
        )
        PrimaryButton(
            modifier = Modifier,
            text = "Primary Button disabled",
            isEnabled = false,
            onClick = {}
        )
        SecondaryButton(
            modifier = Modifier,
            text = "Secondary Button enabled",
            isEnabled = true,
            onClick = {}
        )
        SecondaryButton(
            modifier = Modifier,
            text = "Secondary Button disabled",
            isEnabled = false,
            onClick = {}
        )
        CircleButton(icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add button"
            )
        },
            modifier = Modifier,
            onClick = {}
        )
        SquareButton(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "notifications"
                )
            },
            isEnabled = true
        ){}
        SmallButton(
            modifier = Modifier,
            text = "Small Button enabled",
            isEnabled = true,
            onClick = {}
        )
        SmallButton(
            modifier = Modifier,
            text = "Small Button disabled",
            isEnabled = false,
            onClick = {}
        )
    }
}

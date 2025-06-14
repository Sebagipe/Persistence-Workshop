package com.example.persistence.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.persistence.viewmodels.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isAlwaysOnScreen: Boolean,
    viewModel: SettingsViewModel = viewModel<SettingsViewModel>(),
    ) {

    Column (modifier = modifier) {
        SettingsItem(
            text = "Display Always On",
            isChecked = isAlwaysOnScreen,
            onCheckedChange = {viewModel.setAlwaysOnDisplay(!isAlwaysOnScreen)}
        )
    }
}


@Preview
@Composable
fun SettingsItemPreview() {
    SettingsItem(text = "Test", isChecked = false, onCheckedChange = {})
}

@Composable
fun SettingsItem(
    text: String,
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked,
            onCheckedChange =  onCheckedChange ,
        )
    }
}
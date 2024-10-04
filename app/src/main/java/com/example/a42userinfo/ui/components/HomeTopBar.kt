package com.example.a42userinfo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.a42userinfo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String, onLogOutClick: () -> Unit = {}
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ), actions = {
        IconButton(onClick = { isDialogVisible = true }) {
            Icon(
                imageVector = Icons.Filled.Logout,
                contentDescription = stringResource(R.string.tool_bar_exit_content_description),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        if (isDialogVisible) {
            CustomDialog(onConfirm = {
                isDialogVisible = false
                onLogOutClick()
            }, onDismiss = {
                isDialogVisible = false
            }, bodyText = stringResource(R.string.dialog_logout_text))
        }
    })
}
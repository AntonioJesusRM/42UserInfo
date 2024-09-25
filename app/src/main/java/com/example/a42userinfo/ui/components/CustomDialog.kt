package com.example.a42userinfo.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.a42userinfo.R
import com.example.a42userinfo.ui.theme.CustomDialogTheme

@Composable
fun CustomDialog(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    bodyText: String,
) {
    CustomDialogTheme {
        AlertDialog(text = {
            Text(text = bodyText)
        }, onDismissRequest = onDismiss, confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text(stringResource(R.string.dialog_accept))
            }
        }, dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(stringResource(R.string.dialog_cancel))
            }
        })
    }
}
package com.example.a42userinfo.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.a42userinfo.R

@Composable
fun <T> ListElements(elements: List<T>, title: String, renderElement: @Composable (T) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
            )
            Box(
                modifier = Modifier.animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500, easing = LinearOutSlowInEasing
                    )
                )
            ) {
                if (expanded) {
                    PrintElements(elements, renderElement = renderElement)
                }
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.list_component_show_less)
                } else {
                    stringResource(R.string.list_component_show_more)
                }
            )
        }
    }
}

@Composable
fun <T> PrintElements(elements: List<T>, renderElement: @Composable (T) -> Unit) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = elements) { project ->
            ElementColumn(project, renderElement = renderElement)
        }
    }
}

@Composable
fun <T> ElementColumn(element: T, renderElement: @Composable (T) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)

    ) {
        renderElement(element)
    }
}
package com.bagmanovam.news.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bagmanovam.news.R

@Composable
fun SubscriptionChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onSubscriptionClick: (String) -> Unit,
    onDeleteSubscriptionClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = {
            onSubscriptionClick(text)
        },
        label = {
            Text(text = text)
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier
                    .size(16.dp),
                onClick = { onDeleteSubscriptionClick() },
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.remove_subscription)
                )
            }
        }
    )
}
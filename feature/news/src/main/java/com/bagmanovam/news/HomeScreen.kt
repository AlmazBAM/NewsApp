@file:OptIn(ExperimentalMaterial3Api::class)

package com.bagmanovam.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagmanovam.news.components.SubscriptionChip
import com.bagmanovam.ui.NewsAppTheme


private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit,
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)

    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = { Text(text = stringResource(R.string.my_news)) },
            actions = {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp),
                    onClick = { onAction(HomeScreenAction.OnRefreshData) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.update_articles)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(8.dp),
                    onClick = { onAction(HomeScreenAction.OnClearArticles) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_articles)
                    )
                }
            },
            windowInsets = WindowInsets()
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.topic,
            onValueChange = {
                onAction(HomeScreenAction.InputTopic(it))
            },
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.add_subscription))
            },
            placeholder = {
                Text(text = stringResource(R.string.what_interests_you))
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAction(HomeScreenAction.OnClickSubscribe) },
            enabled = state.subscribeButtonEnabled
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_subscription)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.add_subscription_button))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (state.subscriptions.isNotEmpty()) {
            Text(
                text = stringResource(
                    R.string.subscriptions_label, state.subscriptions.size
                ),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.subscriptions.forEach { (topic, isSelected) ->
                    item(key = topic) {
                        SubscriptionChip(
                            text = topic,
                            isSelected = isSelected,
                            onSubscriptionClick = { onAction(HomeScreenAction.OnToggleTopicSelection(topic)) },
                            onDeleteSubscriptionClick = { onAction(HomeScreenAction.RemoveSubscription(topic)) }
                        )
                    }
                }
            }
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.no_subscriptions),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    NewsAppTheme {
        HomeScreen(
            state = HomeScreenState(
                topic = "",
                subscriptions = mapOf(
                    "Android" to true
                ),
                articles = emptyList()
            ),
            onAction = {}
        )
    }
}

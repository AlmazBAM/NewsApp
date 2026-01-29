package com.bagmanovam.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.bagmanovam.domain.model.Article
import com.bagmanovam.news.R
import com.bagmanovam.ui.CustomIcons

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClick: (Article) -> Unit,
) {
    Card(
        modifier = modifier.clickable(
            enabled = true,
            onClickLabel = null,
            role = null,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { onArticleClick(article) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        article.imageUrl?.let { imageUrl ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                model = imageUrl,
                contentDescription = stringResource(R.string.image_for_article, article.title),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (article.description.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = article.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.sourceName,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = article.publishedAt.toString(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val context = LocalContext.current

            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = CustomIcons.OpenInNew,
                    contentDescription = stringResource(R.string.read_article)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.read))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.share_article)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.share))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
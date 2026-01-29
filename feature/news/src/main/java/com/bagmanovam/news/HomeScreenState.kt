package com.bagmanovam.news

import androidx.compose.runtime.Immutable
import com.bagmanovam.domain.model.Article

@Immutable
data class HomeScreenState(
    val topic: String = "",
    val isLoading: Boolean = false,
    val subscriptions: Map<String, Boolean> = mapOf(),
    val articles: List<Article> = listOf(),
    val addStatus: AddStatus = AddStatus.Idle
) {
    val selectedTopics: List<String>
        get() = subscriptions.filter { it.value }.map { it.key }

    val subscribeButtonEnabled: Boolean
        get() = topic.isNotBlank()
}


sealed interface AddStatus {
    data object Idle : AddStatus
    data object Loading : AddStatus
    data object Success : AddStatus
    data class Error(val message: String? = null) : AddStatus
}
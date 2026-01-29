package com.bagmanovam.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagmanovam.domain.usecase.AddSubscriptionUseCase
import com.bagmanovam.domain.usecase.ClearAllArticlesUseCase
import com.bagmanovam.domain.usecase.GetAllSubscriptionsUseCase
import com.bagmanovam.domain.usecase.GetArticlesByTopicsUseCase
import com.bagmanovam.domain.usecase.RemoveSubscriptionUseCase
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val addSubscriptionUseCase: AddSubscriptionUseCase,
    private val clearAllArticlesUseCase: ClearAllArticlesUseCase,
    private val getAllSubscriptionsUseCase: GetAllSubscriptionsUseCase,
    private val getArticleByTopicsUseCase: GetArticlesByTopicsUseCase,
    private val removeSubscriptionUseCase: RemoveSubscriptionUseCase,
    private val updateArticlesForAllSubscriptionsUseCase: UpdateArticlesForAllSubscriptionsUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState())
    val state = _state
        .onStart {
            observeSelectedTopics()
            observeSubscriptions()
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = HomeScreenState()
        )

    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.InputTopic -> {
                _state.update { state ->
                    state.copy(
                        topic = action.topic
                    )
                }
            }

            HomeScreenAction.OnClearArticles -> {
                viewModelScope.launch {
                    _state.value.apply {
                        clearAllArticlesUseCase(selectedTopics)
                    }
                }
            }

            HomeScreenAction.OnClickSubscribe -> {
                viewModelScope.launch {
                    addSubscriptionUseCase(_state.value.topic)
                    _state.update { state ->
                        state.copy(topic = "")
                    }
                }
            }

            HomeScreenAction.OnRefreshData -> {
                viewModelScope.launch {
                    updateArticlesForAllSubscriptionsUseCase()
                }
            }

            is HomeScreenAction.OnToggleTopicSelection -> {
                _state.update { state ->
                    val subscriptions = state.subscriptions.toMutableMap()
                    val isSelected = subscriptions[action.topic] ?: false
                    subscriptions[action.topic] = !isSelected
                    state.copy(
                        subscriptions = subscriptions
                    )
                }
            }

            is HomeScreenAction.RemoveSubscription -> {
                viewModelScope.launch {
                    removeSubscriptionUseCase(action.topic)
                }
            }
        }
    }

    private fun observeSelectedTopics() {
        state.map { state ->
            state.selectedTopics
        }
            .distinctUntilChanged()
            .flatMapLatest { topics ->
                getArticleByTopicsUseCase(topics)
            }
            .onEach {
                _state.update { state ->
                    state.copy(
                        articles = it
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeSubscriptions() {
        getAllSubscriptionsUseCase()
            .onEach { subscriptions ->
                _state.update { prev ->
                    prev.copy(
                        subscriptions = subscriptions.associateWith { topic ->
                            prev.subscriptions[topic] ?: true
                        }
                    )
                }
            }.launchIn(viewModelScope)
    }
}
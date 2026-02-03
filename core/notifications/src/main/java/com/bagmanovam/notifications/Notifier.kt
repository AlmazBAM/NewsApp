package com.bagmanovam.notifications

import com.bagmanovam.domain.model.Article

interface Notifier {

    fun postNotifications(topics: List<String>)
}
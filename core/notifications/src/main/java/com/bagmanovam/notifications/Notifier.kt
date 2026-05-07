package com.bagmanovam.notifications

interface Notifier {

    fun postNotifications(topics: List<String>)
}
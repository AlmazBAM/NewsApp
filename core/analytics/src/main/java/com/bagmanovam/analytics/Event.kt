package com.bagmanovam.analytics

data class AnalyticsEvent(
    val name: String,
    val params: Map<String, Any>? = null,
) {
    class Types {
        companion object {
            const val SCREEN_VIEW = "screen_view"
            const val BUTTON_CLICK = "button_click"
        }
    }
    class ParamKeys {
        companion object {
            const val SCREEN_NAME = "screen_name"
        }
    }
}
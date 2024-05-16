package com.ehsieh2.letswatchtv.animationSettings

import android.content.Context

object AppPreferences {
    fun isAnimationEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("AnimationEnabled", true)
    }
}

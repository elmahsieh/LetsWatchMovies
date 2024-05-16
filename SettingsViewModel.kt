package com.ehsieh2.letswatchtv

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs: SharedPreferences = application.getSharedPreferences("settings", MODE_PRIVATE)

    private val _useApi = MutableLiveData<Boolean>()
    val useApi: LiveData<Boolean> = _useApi

    init {
        _useApi.value = prefs.getBoolean("useApi", true)
    }

    fun saveUseApiSetting(useApi: Boolean) {
        prefs.edit().putBoolean("useApi", useApi).apply()
        _useApi.postValue(useApi)  // Use postValue to update LiveData from a background thread if needed.
    }
}
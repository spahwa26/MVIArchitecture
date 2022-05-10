package com.nickelfox.myfinaltest.data.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val prefManager by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    var authToken: String?
        get() = prefManager.getString(AUTH_TOKEN, null)
        set(value) = prefManager.edit().putString(AUTH_TOKEN, value).apply()

    var deviceToken: String
        get() = prefManager.getString(DEVICE_TOKEN, null) ?: ""
        set(value) = prefManager.edit().putString(DEVICE_TOKEN, value).apply()


    companion object {
        private const val ONBOARDING_COMPLETED = "onboarding_completed"
        private const val AUTH_TOKEN = "auth_token"
        private const val DEVICE_TOKEN="DEVICE_TOKEN"
    }
}

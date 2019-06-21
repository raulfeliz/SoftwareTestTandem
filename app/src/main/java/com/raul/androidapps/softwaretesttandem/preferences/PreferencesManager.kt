package com.raul.androidapps.softwaretesttandem.preferences

import android.content.Context
import android.preference.PreferenceManager
import com.raul.androidapps.softwaretesttandem.security.Encryption
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(private var context: Context) {

    fun getIntFromPreferences(key: String): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(key, -1)
    }

    fun getStringFromPreferences(key: String): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, "") ?: ""
    }

    fun getBooleanFromPreferences(key: String): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean(key, false)
    }

    fun getLongFromPreferences(key: String): Long {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getLong(key, (-1).toLong())
    }

    fun getFloatFromPreferences(key: String): Float {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getFloat(key, (-1).toFloat())
    }

    fun setIntIntoPreferences(key: String, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putInt(key, value).apply()
    }

    fun setStringIntoPreferences(key: String, value: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(key, value).apply()
    }

    fun setEncryptedStringIntoPreferences(key: String, value: String, alias: String): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val encryptedText = Encryption.encryptString(context, value, alias)
        return if (encryptedText.isNotBlank()) {
            prefs.edit().putString(key, encryptedText).apply()
            true
        } else {
            prefs.edit().putString(key, value).apply()
            false
        }
    }

    fun setBooleanIntoPreferences(key: String, value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean(key, value).apply()
    }

    fun setLongIntoPreferences(key: String, value: Long) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putLong(key, value).apply()
    }

    fun setFloatIntoPreferences(key: String, value: Float) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putFloat(key, value).apply()
    }

    fun deleteVarFromSharedPreferences(key: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().remove(key).apply()
    }

    fun containsKey(key: String): Boolean =
        PreferenceManager.getDefaultSharedPreferences(context).contains(key)

    fun clearAll() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().clear().apply()
    }
}
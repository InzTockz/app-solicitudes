package com.battilana.app_solicitudes.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.battilana.app_solicitudes.data.model.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context, private val json: Json) {

    companion object {
        private val USER_SESSION_KEY = stringPreferencesKey("user_session")
    }

    suspend fun saveUserSession(userSession: UserSession){
        val jsonString = json.encodeToString(userSession)
        context.dataStore.edit { preferences ->
            preferences[USER_SESSION_KEY] = jsonString
        }
    }

    val userSession: Flow<UserSession?> = context.dataStore.data.map { preferences ->
        preferences[USER_SESSION_KEY]?.let { jsonString ->
            try {
                json.decodeFromString<UserSession>(jsonString)
            } catch (_: Exception){
                null
            }
        }
    }

    suspend fun clearSession(){
        context.dataStore.edit { it.clear() }
    }
}
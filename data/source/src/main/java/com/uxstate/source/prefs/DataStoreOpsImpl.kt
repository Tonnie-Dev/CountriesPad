package com.uxstate.source.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.uxstate.util.PREFS_NAME
import com.uxstate.util.model.AppPrefs
import com.uxstate.util.model.ThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreOpsImpl @Inject constructor(@ApplicationContext private val context: Context) :
    DataStoreOps {

    //Create a DataStore instance
    private val Context.dataStore by preferencesDataStore(PREFS_NAME)


    //.data returns a flow representing the current state of the data
    override val appPrefs: Flow<AppPrefs> = context.dataStore.data

            .map { prefs ->

                //use the above key to read the current prefs value
                val themeMode =
                    ThemeMode.valueOf(prefs[THEME_MODE] ?: ThemeMode.SYSTEM_DEFAULT.name)

                AppPrefs(themeMode = themeMode)
            }

    override suspend fun updateTheme(themeMode: ThemeMode) {

        context.dataStore.edit {

            it[THEME_MODE] = themeMode.name
        }
    }


    //private Companion object to centralize the key preferences
    private companion object {

        //use booleanPreferencesKey() to create a key
        val THEME_MODE = stringPreferencesKey("theme_mode")
    }
}
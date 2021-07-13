package ar.com.wolox.android.example.utils

import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.util.SharedPreferencesManager

import javax.inject.Inject

@ApplicationScope
class UserSession @Inject constructor(private val sharedPreferencesManager: SharedPreferencesManager) {

    // Really, we don't need to query the username because this instance live as long as the
    // application, but we should add a check in case Android decides to kill the application
    // and return to a state where this isn't initialized.
    var username: String? = null
        // Para setear u obtener el username guardado en el sharedPreferences
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.USERNAME, null].also {
            field = it
        }
        set(username) {
            field = username
            sharedPreferencesManager.store(Extras.UserLogin.USERNAME, field)
        }

    var password: String? = null
        // Para setear u obtener la password guardado en el sharedPreferences
        get() = field ?: sharedPreferencesManager[Extras.UserLogin.PASSWORD, null].also {
            field = it
        }
        set(password) {
            field = password
            sharedPreferencesManager.store(Extras.UserLogin.PASSWORD, field)
        }

    var loginOk: Boolean = false
        // Para saber si fueron guardados los datos
        get() = sharedPreferencesManager[Extras.UserLogin.USERNAME, null] != null
}

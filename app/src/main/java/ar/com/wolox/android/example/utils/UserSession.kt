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
    var password: String? = null
    var loginOk: Boolean = false

    // Para setear u obtener el username guardado en el sharedPreferences
    fun get_Username() = username ?: sharedPreferencesManager[Extras.UserLogin.USERNAME, null].also {
            username = it
        }
    fun set_Username(username: String) {
            this.username = username
            sharedPreferencesManager.store(Extras.UserLogin.USERNAME, username)
        }

    // Para setear u obtener la password guardado en el sharedPreferences
    fun get_Password() = password ?: sharedPreferencesManager[Extras.UserLogin.PASSWORD, null].also {
        password = it
    }
    fun set_Password(password: String) {
        this.password = password
        sharedPreferencesManager.store(Extras.UserLogin.PASSWORD, password)
    }

    // Para saber si fueron guardados los datos
    fun isLogin() = sharedPreferencesManager[Extras.UserLogin.USERNAME, null] != null
}

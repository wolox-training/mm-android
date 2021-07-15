package ar.com.wolox.android.example.utils

import android.app.Activity
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

/**
 * Util class to store keys to use with [SharedPreferences] or as argument between
 * [Fragment] or [Activity].
 */
object Extras {

    object Constantes {
        const val URL_WOLOX = "https://www.wolox.com.ar"
        const val ERROR_NETWORK = "error network"
    }

    object UserLogin {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val VALID_EMAIL = "invalid email"
        const val ACCESS_TOKEN = "access_token"
        const val UID = "uid"
        const val CLIENT = "client"
    }

    object ViewPager {
        const val FAVOURITE_COLOR_KEY = "FAVOURITE_COLOR_KEY"
    }
}

package ar.com.wolox.android.example.utils

import android.app.Activity
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Util class to store keys to use with [SharedPreferences] or as argument between
 * [Fragment] or [Activity].
 */
object Extras {

    object Constantes {
        const val URL_WOLOX = "https://www.wolox.com.ar"
        const val ERROR_USER_PASS = "error email pass"
        const val ERROR_NETWORK = "error network"
        const val ERROR_GENERIC = "error generic"
        const val NO_MORE_NEWS = "no more news"

        //TEST
        const val login_test_email = "melvin.lambert15@example.com"
        const val login_test_password = "123456"
        const val login_test_id = 6
        const val login_test_provider = "email"
        const val login_test_uid = "melvin.lambert15@example.com"
        const val login_test_allow_password_change = false
        const val login_test_name = "Melvin Lambert"
        const val login_test_nickname = "Melvin"
        const val login_test_image = ""

        const val login_test_header_accessToken = "I54n43S_l62NDKWXbuZaSA"
        const val login_test_header_client = "P-DyyGXKtHWrc1Lg5wnY2g"
        const val login_test_header_uid = "melvin.lambert15@example.com"
    }

    object UserLogin {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val VALID_EMAIL = "invalid email"
        const val ACCESS_TOKEN = "access_token"
        const val UID = "uid"
        const val CLIENT = "client"
    }

    //Convierte una fecha pasada como string a Date, en el formato yyyy-MM-dd
    fun convertToDate(strdate : String) : Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        var convertedDate : Date?= null

        try {
            convertedDate = dateFormat.parse(strdate)
        } catch (e : ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return convertedDate
    }
}

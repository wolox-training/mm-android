package ar.com.wolox.android.example.ui.example.presenter

import android.view.View
import androidx.core.util.PatternsCompat
import ar.com.wolox.android.example.model.AuthenticationBody
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.UserRepository
import ar.com.wolox.android.example.ui.example.view.LoginView
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val userSession: UserSession, private val userRepository: UserRepository) :
    CoroutineBasePresenter<LoginView>() {

    fun onLoginButtonClicked(user: String, pass: String) {
        // Validar vacios y formato de email
        if (user.isEmpty())
            view?.showError(Extras.UserLogin.USERNAME)
        else {
            if (!isValidEmail(user))
                view?.showError(Extras.UserLogin.VALID_EMAIL)
        }

        if (pass.isEmpty())
            view?.showError(Extras.UserLogin.PASSWORD)

        if (!user.isEmpty() && !pass.isEmpty()) {
            // Mostrar progressbar
            view?.showLoading(View.VISIBLE)

            loginNetwork(user, pass)
        }
    }

    // Para verificar el user and pass al endpoint
    fun loginNetwork(user: String, pass: String) = launch {
        val authbody = AuthenticationBody(user, pass)

        // Llamado al endpoint
        networkRequest(userRepository.signIn(authbody)) {
            onResponseSuccessful { userequest, headers ->
                // Si el login fue satisfactorio, guardamos los datos y continuamos con el Home
                userSession.apply {
                    loginOk = true
                    username = user
                    password = pass
                    acces_token = headers?.get("Access-Token")
                    uid = headers?.get("Uid")
                    client = headers?.get("Client")

                    //Guardo el id del usuario para que se use en otras instancias
                    if (userequest != null) {
                        id = userequest.data.id
                    }
                }
                // Ocultar progressbar
                view?.showLoading(View.GONE)

                view?.showHome()
            }
            onResponseFailed { _, code ->
                if (code == 401)
                    view?.showError(Extras.Constantes.ERROR_USER_PASS)
                else
                    view?.showError(Extras.Constantes.ERROR_GENERIC)

                // Ocultar progressbar
                view?.showLoading(View.GONE)
            }
            onCallFailure {
                view?.showError(Extras.Constantes.ERROR_NETWORK)

                // Ocultar progressbar
                view?.showLoading(View.GONE)
            }
        }
    }

    // Para cuando se clickea sobre el boton signup
    fun onSignUpButtonClicked() {
        view?.showSignUp()
    }

    // Para cuando se clickea sobre el link terminos y condiciones
    fun onTermsClicked() {
        view?.showTerms()
    }

    // Para validar que sea un tipo email
    fun isValidEmail(email: String) = !email.isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    // Para obtener el username guardado
    fun getUserNameSaved() = userSession.username

    // Para obtener la password guardada
    fun getPasswordSaved() = userSession.password

    // Cargar datos iniciales si fueron guardados
    fun checkDataSaved() {
        if (userSession.loginOk)
            view?.setDataSaved()
    }
}

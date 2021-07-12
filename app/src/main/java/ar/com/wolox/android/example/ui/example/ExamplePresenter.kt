package ar.com.wolox.android.example.ui.example

import android.util.Patterns
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ExamplePresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<ExampleView>() {

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
            userSession.loginOk = true
            userSession.set_Username(user)
            userSession.set_Password(pass)

            view?.showHome()
        }

        // Forma que tenia para ir validando de acuerdo al campo
//        when {
//            user.isEmpty() -> view?.showError(Extras.UserLogin.USERNAME)
//            pass.isEmpty() -> view?.showError(Extras.UserLogin.PASSWORD)
//            !isValidEmail(user) -> view?.showError(Extras.UserLogin.VALID_EMAIL)
//            else -> {
//                userSession.loginOk = true
//                userSession.set_Username(user)
//                userSession.set_Password(pass)
//            }
//        }
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
    fun isValidEmail(email: String) = !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // Para obtener el username guardado
    fun getUserNameSaved() = userSession.get_Username()

    // Para obtener la password guardada
    fun getPasswordSaved() = userSession.get_Password()

    // Cargar datos iniciales si fueron guardados
    fun checkDataSaved() {
        if (userSession.isLogin())
            view?.setDataSaved()
    }
}

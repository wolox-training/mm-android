package ar.com.wolox.android.example.ui.example

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    override fun layout() = R.layout.fragment_login

    override fun init() {
        // Validar si hay datos guardados
        presenter.checkDataSaved()
    }

    override fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.onLoginButtonClicked(etUsername.text.toString(), etPassword.text.toString())
            }
            btnSignup.setOnClickListener {
                presenter.onSignUpButtonClicked()
            }
            tvTerms.setOnClickListener {
                presenter.onTermsClicked()
            }
        }
    }

    // Mostrar los errores en cada campo
    override fun showError(tipo: String) {
        with(binding) {
            when (tipo) {
                Extras.UserLogin.USERNAME -> etUsername.error =
                    (getString(R.string.fragment_example_empty_value))
                Extras.UserLogin.PASSWORD -> etPassword.error =
                    (getString(R.string.fragment_example_empty_value))
                Extras.UserLogin.VALID_EMAIL -> etUsername.error =
                    (getString(R.string.fragment_example_error_email))
            }
        }
    }

    // Guardar los datos ingresados
    override fun setDataSaved() {
        with(binding) {
            etUsername.setText(presenter.getUserNameSaved())
            etPassword.setText(presenter.getPasswordSaved())
        }
    }

    override fun showHome() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun showSignUp() {
        val intent = Intent(context, SignUpActivity::class.java)
        startActivity(intent)
    }

    override fun showTerms() {
        context?.openBrowser(Extras.Constantes.URL_WOLOX)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}

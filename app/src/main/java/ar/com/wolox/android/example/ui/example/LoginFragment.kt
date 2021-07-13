package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentExampleBinding
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class LoginFragment private constructor() : WolmoFragment<FragmentExampleBinding, LoginPresenter>(), LoginView {

    override fun layout() = R.layout.fragment_example

    override fun init() {
        // Validar si hay datos guardados
        presenter.checkDataSaved()
    }

    override fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.onLoginButtonClicked(userName.text.toString(), password.text.toString())
            }
        }
    }

    // Mostrar los errores en cada campo
    override fun showError(tipo: String) {
        with(binding) {
            when (tipo) {
                Extras.UserLogin.USERNAME -> userName.error =
                    (getString(R.string.fragment_example_empty_value))
                Extras.UserLogin.PASSWORD -> password.error =
                    (getString(R.string.fragment_example_empty_value))
                Extras.UserLogin.VALID_EMAIL -> userName.error =
                    (getString(R.string.fragment_example_error_email))
            }
        }
    }

    // Guardar los datos ingresados
    override fun setDataSaved() {
        with(binding) {
            userName.setText(presenter.getUserNameSaved())
            password.setText(presenter.getPasswordSaved())
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}

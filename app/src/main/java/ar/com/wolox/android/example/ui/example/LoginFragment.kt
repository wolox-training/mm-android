package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.openBrowser
import javax.inject.Inject

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_login

    override fun init() {
        // Validar si hay datos guardados
        presenter.checkDataSaved()
    }

    override fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.onLoginButtonClicked(userName.text.toString(), password.text.toString())
            }
            btnSignup.setOnClickListener {
                presenter.onSignUpButtonClicked()
            }
            terms.setOnClickListener {
                presenter.onTermsClicked()
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
                Extras.Constantes.ERROR_USER_PASS -> toastFactory.show(R.string.fragment_example_error_email_pass)
                Extras.Constantes.ERROR_NETWORK -> toastFactory.show(R.string.fragment_example_error_network)
                Extras.Constantes.ERROR_GENERIC -> toastFactory.show(R.string.unknown_error)
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

    override fun showHome() {
        HomeActivity.start(requireContext())
    }

    override fun showSignUp() {
        SignUpActivity.start(requireContext())
    }

    override fun showTerms() {
        context?.openBrowser(Extras.Constantes.URL_WOLOX)
    }

    override fun showLoading(visibility: Int) {
        with(binding) {
            progress.visibility = visibility
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}

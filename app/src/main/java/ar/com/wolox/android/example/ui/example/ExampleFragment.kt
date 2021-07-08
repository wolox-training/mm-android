package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentExampleBinding
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class ExampleFragment private constructor() : WolmoFragment<FragmentExampleBinding, ExamplePresenter>(), ExampleView {

    companion object {
        fun newInstance() = ExampleFragment()
    }

    override fun layout() = R.layout.fragment_example

    override fun init() {
        // Validar si hay datos guardados
        presenter.checkDataSaved()
    }

    override fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.onLoginButtonClicked(etUsername.text.toString(), etPassword.text.toString())
            }
        }
    }

    // Mostrar los errores en cada campo
    override fun showError(tipo: String) {
        when (tipo) {
            Extras.UserLogin.USERNAME -> binding.etUsername.error = (getString(R.string.fragment_example_empty_value))
            Extras.UserLogin.PASSWORD -> binding.etPassword.error = (getString(R.string.fragment_example_empty_value))
            Extras.UserLogin.VALID_EMAIL -> binding.etUsername.error = (getString(R.string.fragment_example_error_email))
        }
    }

    // Guardar los datos ingresados
    override fun setDataSaved() {
        binding.etUsername.setText(presenter.getUserNameSaved())
        binding.etPassword.setText(presenter.getPasswordSaved())
    }
}

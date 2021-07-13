package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentExampleBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class ExampleFragment private constructor() : WolmoFragment<FragmentExampleBinding, ExamplePresenter>(), ExampleView {

    override fun layout() = R.layout.fragment_example

    override fun init() {
    }

    override fun setListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                presenter.onLoginButtonClicked(userName.text.toString(), password.text.toString())
            }
        }
    }

    override fun toggleLoginButtonEnable(isEnable: Boolean) {
        binding.btnLogin.isEnabled = isEnable
    }

    companion object {
        fun newInstance() = ExampleFragment()
    }
}

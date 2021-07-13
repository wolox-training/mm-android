package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentSignupBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class SignUpFragment private constructor() : WolmoFragment<FragmentSignupBinding, SignUpPresenter>(), SignUpView {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun layout() = R.layout.fragment_signup

    override fun init() {
    }
}
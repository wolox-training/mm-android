package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentMainBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class MainFragment private constructor() : WolmoFragment<FragmentMainBinding, MainPresenter>(), MainView {

    override fun layout() = R.layout.fragment_main

    override fun init() {
        // Si el usuario no está logueado redirigir al Login
        if (!presenter.checkUserLogin())
            LoginActivity.start(requireContext())
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

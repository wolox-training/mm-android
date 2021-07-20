package ar.com.wolox.android.example.ui.example.fragment

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentMainBinding
import ar.com.wolox.android.example.ui.example.presenter.MainPresenter
import ar.com.wolox.android.example.ui.example.view.MainView
import ar.com.wolox.android.example.ui.example.activity.HomeActivity
import ar.com.wolox.android.example.ui.example.activity.LoginActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class MainFragment private constructor() : WolmoFragment<FragmentMainBinding, MainPresenter>(),
    MainView {

    override fun layout() = R.layout.fragment_main

    override fun init() {
        // Si el usuario no está logueado redirigir al Login
        if (!presenter.checkUserLogin())
            LoginActivity.start(requireContext())
        else
            HomeActivity.start(requireContext())
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

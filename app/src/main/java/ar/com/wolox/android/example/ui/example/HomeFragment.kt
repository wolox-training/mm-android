package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomeBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class HomeFragment private constructor() : WolmoFragment<FragmentHomeBinding, HomePresenter>(), HomeView {

    override fun layout() = R.layout.fragment_home

    override fun init() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
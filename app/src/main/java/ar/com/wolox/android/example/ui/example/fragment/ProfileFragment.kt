package ar.com.wolox.android.example.ui.example.fragment

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentProfileBinding
import ar.com.wolox.android.example.ui.example.presenter.ProfilePresenter
import ar.com.wolox.android.example.ui.example.view.ProfileView
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<FragmentProfileBinding, ProfilePresenter>(),
    ProfileView {

    override fun layout() = R.layout.fragment_profile

    override fun init() {
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}
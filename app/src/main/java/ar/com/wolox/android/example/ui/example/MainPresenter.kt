package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<MainView>() {

    // Chequear si el usuario está logueado
    fun checkUserLogin() = userSession.loginOk
}
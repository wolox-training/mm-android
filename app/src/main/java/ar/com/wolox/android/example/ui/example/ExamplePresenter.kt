package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ExamplePresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<ExampleView>() {

    fun onLoginButtonClicked(user: String, pass: String) {
        userSession.username = user
    }
}

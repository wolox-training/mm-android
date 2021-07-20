package ar.com.wolox.android.example.ui.example.view

interface LoginView {

    fun showError(tipo: String)

    fun setDataSaved()

    fun showHome()

    fun showSignUp()

    fun showTerms()

    fun showLoading(visibility: Int)
}

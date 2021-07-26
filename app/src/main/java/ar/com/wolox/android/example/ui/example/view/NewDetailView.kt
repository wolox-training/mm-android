package ar.com.wolox.android.example.ui.example.view

import ar.com.wolox.android.example.model.News

interface NewDetailView{

    fun loadData(new : News)

    fun showLoading(visibility: Int)

    fun showError(tipo: String)

    fun changeLike()

    fun enabledLike(enabled : Boolean)

    fun clearRefreshing()
}

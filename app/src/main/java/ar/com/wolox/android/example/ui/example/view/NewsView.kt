package ar.com.wolox.android.example.ui.example.view

import ar.com.wolox.android.example.model.News

interface NewsView{

    fun setDataNews(item : News)

    fun showLoading(visibility: Int)

    fun showError(tipo: String)

    fun adapterRefresh()
}

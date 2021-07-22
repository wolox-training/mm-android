package ar.com.wolox.android.example.ui.example.presenter

import android.view.View
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.RequestNews
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.network.repository.UserRepository
import ar.com.wolox.android.example.ui.example.view.LoginView
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.ui.example.viewholder.NewsViewHolder
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.Extras.convertToDate
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val userSession: UserSession, private val newsRepository: NewsRepository) :
    CoroutineBasePresenter<NewsView>(){

    private var items : MutableList<News> = mutableListOf()
    private var page = 1

    //Para cargar todos los items
    fun addAllItemsNews(items : List<News>?){
        if(items!=null)
            this.items.addAll(items)
    }

    fun onBindNewsViewAtPosition(holder: NewsViewHolder, position: Int){
        holder.apply {
            //Cargar los datos de las noticias
            setDataNews(items[position])
        }
    }

    fun getNewsRowsCount() : Int = items.size

    fun getNews(fromPullRefresh : Boolean) = launch{
        // Si es por pull refresh consulto page=1 y no muestro progressbar
        var currentpage=page
        if(fromPullRefresh)
            currentpage=1
        else
            view?.showLoading(View.VISIBLE)

        // Llamado al endpoint
        networkRequest(newsRepository.getNews(currentpage)) {
            onResponseSuccessful { requestNews, _ ->

                //Verificar que hay noticias
                if(requestNews==null)
                    view?.showError(Extras.Constantes.ERROR_GENERIC)
                else {
                    if (requestNews.page.isEmpty())
                        view?.showError(Extras.Constantes.NO_MORE_NEWS)
                    else {
                        //Verificar si se hizo un pull refresh
                        if(fromPullRefresh){
                            compareItems(requestNews.page)

                            view?.clearRefreshing()
                        }
                        else {
                            addAllItemsNews(requestNews.page)

                            //Guardo siguiente pagina para buscar
                            page = requestNews.next_page
                        }

                        view?.adapterRefresh()
                    }
                }

                // Ocultar progressbar
                view?.showLoading(View.GONE)

            }
            onResponseFailed { _, _ ->
                view?.apply {
                    showError(Extras.Constantes.ERROR_GENERIC)

                    // Ocultar progressbar
                    showLoading(View.GONE)
                    clearRefreshing()
                }
            }
            onCallFailure {
                view?.apply {
                    showError(Extras.Constantes.ERROR_NETWORK)

                    // Ocultar progressbar
                    showLoading(View.GONE)
                    clearRefreshing()
                }
            }
        }
    }

    //Comparar si hay items nuevos para agregar adelante de la lista
    fun compareItems(newitems : List<News>){
        val firtselement=items[0].date.convertToDate()
        val newitemsaux =newitems.filter { it.date.convertToDate()!!.isAfter(firtselement) }

        //Si no hay noticias nuevas, muestro mensaje
        if(newitemsaux.isEmpty()) {
            view?.showError(Extras.Constantes.NO_MORE_NEWS)
        }
        else {
            newitemsaux.forEach { items.add(0,it) }
        }
    }
}

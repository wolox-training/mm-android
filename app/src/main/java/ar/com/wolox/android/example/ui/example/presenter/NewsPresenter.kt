package ar.com.wolox.android.example.ui.example.presenter

import android.view.View
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.network.repository.UserRepository
import ar.com.wolox.android.example.ui.example.view.LoginView
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.ui.example.viewholder.NewsViewHolder
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val userSession: UserSession, private val newsRepository: NewsRepository) :
    CoroutineBasePresenter<NewsView>(){

    private var items : MutableList<News> = mutableListOf()
    private lateinit var bindingHolder: ItemNewBinding

    //Agregar el binding
    fun addBinding(binding : ItemNewBinding){
        bindingHolder=binding
    }

    //Para cargar todos los items
    fun addAllItemsNews(items : MutableList<News>?){
        if(items!=null)
            this.items.addAll(items)
    }

    fun onBindNewsViewAtPosition(holder: NewsViewHolder, position: Int){
        holder.binding=bindingHolder

        //Cargar los datos de las noticias
        holder.setDataNews(items[position])
    }

    fun getNewsRowsCount() : Int = items.size

    fun getNews() = launch{
        // Mostrar progressbar
        view?.showLoading(View.VISIBLE)

        // Llamado al endpoint
        networkRequest(newsRepository.getNews(userSession.acces_token,userSession.client,userSession.uid)) {
            onResponseSuccessful { requestNews, _ ->

                //Verificar que hay noticias
                if (requestNews?.page?.size == 0)
                    view?.showError(Extras.Constantes.NO_MORE_NEWS)
                else {
                    addAllItemsNews(requestNews?.page)
                    view?.adapterRefresh()
                }

                // Ocultar progressbar
                view?.showLoading(View.GONE)

            }
            onResponseFailed { _, _ ->
                view?.showError(Extras.Constantes.ERROR_GENERIC)

                // Ocultar progressbar
                view?.showLoading(View.GONE)
            }
            onCallFailure {
                view?.showError(Extras.Constantes.ERROR_NETWORK)

                // Ocultar progressbar
                view?.showLoading(View.GONE)
            }
        }
    }
}

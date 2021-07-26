package ar.com.wolox.android.example.ui.example.fragment

import android.content.Context
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.BaseConfiguration
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.activity.NewDetailActivity
import ar.com.wolox.android.example.ui.example.adapter.NewsAdapter
import ar.com.wolox.android.example.ui.example.presenter.NewsPresenter
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(),
    NewsView {

    lateinit var adapter : NewsAdapter
    lateinit var lm : LinearLayoutManager

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_news

    override fun init() {
        with(binding){
            lm=LinearLayoutManager(context)
            recyclerview.apply {
                layoutManager=lm
                hasFixedSize()
            }

            //Inicializar las noticias
            presenter.getNews(false)

            adapter = NewsAdapter(presenter)
            recyclerview.adapter=adapter
        }
    }

    override fun setListeners() {
        with(binding) {
            recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    //Chequear si hay que cargar mas datos
                    if (!isLoading() && lm.itemCount <= lm.findLastVisibleItemPosition() + Extras.Constantes.OFFSETVISIBLE) {
                        presenter.getNews(false)
                    }
                }
            })
            swipe.setOnRefreshListener {
                presenter.getNews(true)
            }
        }
    }

    //Frenar el swiprefresh
    override fun clearRefreshing(){
        with(binding) {
            swipe.isRefreshing = false
        }
    }

    // Mostrar los errores en cada campo
    override fun showError(tipo: String) {
        when (tipo) {
            Extras.Constantes.NO_MORE_NEWS -> toastFactory.show(R.string.news_no_more_news)
            Extras.Constantes.ERROR_NETWORK -> toastFactory.show(R.string.fragment_login_error_network)
            Extras.Constantes.ERROR_GENERIC -> toastFactory.show(R.string.unknown_error)
        }
    }

    override fun showLoading(visibility: Int) {
        with(binding) {
            progress.visibility = visibility
        }
    }

    fun isLoading() : Boolean{
        with(binding) {
            return progress.isVisible
        }
    }

    override fun setDataNews(item : News, userid : Int){}

    override fun adapterRefresh(){
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(new : News) {
        NewDetailActivity.start(requireContext(),new)
    }

    //Cuando se resume el fragment, se chequea si se hicieron cambios para refrescar las noticias
    override fun onResume(){
        super.onResume()

        //Verifico si algo cambio de la noticia para refrescar
        val pref = context?.getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        if(pref!=null && pref.getBoolean(Extras.Constantes.NEW_CHANGE,false)) {
            //Limpio la lista
            presenter.clearData()

            //Obtengo nuevamente las noticias
            presenter.getNews(false)

            //Guardo que no hay cambios para que no se refresque siempre
            pref.edit().putBoolean(Extras.Constantes.NEW_CHANGE,false).apply()
        }
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
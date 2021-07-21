package ar.com.wolox.android.example.ui.example.fragment

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.adapter.NewsAdapter
import ar.com.wolox.android.example.ui.example.presenter.NewsPresenter
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import java.util.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(),
    NewsView {

    lateinit var adapter : NewsAdapter

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_news

    override fun init() {
        with(binding){
            recyclerview.layoutManager=LinearLayoutManager(context)
            recyclerview.hasFixedSize()

            //Inicializar las noticias
            presenter.getNews()

            adapter = NewsAdapter(presenter)
            recyclerview.adapter=adapter
        }
    }

    override fun setListeners() {
        with(binding) {
            swipe.setOnRefreshListener {
                //Emulando solicitud al endpoint
                Handler().postDelayed({
                    swipe.isRefreshing = false;
                    toastFactory.show(getString(R.string.news_no_more_news))
                }, 4000);
            }
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

    override fun setDataNews(item : News){}

    override fun adapterRefresh(){
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
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

            //Noticias de pruebas
            val new1=News("1","Titulo 1","Descripción 1",Date(),"https://www.wolox.com.ar/assets/about-us/nurturing_talent.jpg")
            val new2=News("2","Titulo 2","Descripción 2",Date(),"https://www.wolox.com.ar/assets/about-us/nurturing_talent.jpg")
            val new3=News("3","Titulo 3","Descripción 3",Date(),"https://www.wolox.com.ar/assets/about-us/nurturing_talent.jpg")
            val new4=News("4","Titulo 4","Descripción 4",Date(),"https://www.wolox.com.ar/assets/about-us/nurturing_talent.jpg")

            val news= mutableListOf<News>()
            news.add(new1)
            news.add(new2)
            news.add(new3)
            news.add(new4)

            //Inicializar las noticias
            presenter.addAllItemsNews(news)

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

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun setDataNews(item : News){}
}
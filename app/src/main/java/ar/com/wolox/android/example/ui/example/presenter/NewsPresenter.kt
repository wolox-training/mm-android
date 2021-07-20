package ar.com.wolox.android.example.ui.example.presenter

import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.ui.example.viewholder.NewsViewHolder
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<NewsView>(){

    private var items : MutableList<News> = mutableListOf()
    private lateinit var bindingHolder: ItemNewBinding

    //Agregar el binding
    fun addBinding(binding : ItemNewBinding){
        bindingHolder=binding
    }

    //Para cargar todos los items
    fun addAllItemsNews(items : MutableList<News>){
        this.items.addAll(items)
    }

    fun onBindNewsViewAtPosition(holder: NewsViewHolder, position: Int){
        holder.binding=bindingHolder

        //Cargar los datos de las noticias
        holder.setDataNews(items[position])
    }

    fun getNewsRowsCount() : Int = items.size
}

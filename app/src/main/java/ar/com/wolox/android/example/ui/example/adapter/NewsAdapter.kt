package ar.com.wolox.android.example.ui.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.ui.example.presenter.NewsPresenter
import ar.com.wolox.android.example.ui.example.viewholder.NewsViewHolder
import javax.inject.Inject

class NewsAdapter @Inject constructor(private var presenter: NewsPresenter): RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //Inicializar binding
        val binding : ItemNewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_new, parent, false);
        presenter.addBinding(binding)

        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        presenter.onBindNewsViewAtPosition(holder,position);
    }

    override fun getItemCount(): Int {
        return presenter.getNewsRowsCount();
    }
}
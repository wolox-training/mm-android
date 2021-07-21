package ar.com.wolox.android.example.ui.example.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.utils.Extras
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),NewsView {

    lateinit var binding: ItemNewBinding
    private val prettyTime : PrettyTime = PrettyTime()

    fun set(binding:ItemNewBinding){
        this.binding=binding
    }

    override fun setDataNews(item : News){
        with(binding) {

            //Convertir la fecha string a date
            val strdate = Extras.convertToDate(item.date)

            title.text = item.commenter
            description.text = item.comment
            date.text = prettyTime.format(strdate)

            Glide.with(itemView)
                .load(item.avatar)
                .into(image);
        }
    }

    override fun showError(tipo: String) {}

    override fun showLoading(visibility: Int) {}

    override fun adapterRefresh(){}
}
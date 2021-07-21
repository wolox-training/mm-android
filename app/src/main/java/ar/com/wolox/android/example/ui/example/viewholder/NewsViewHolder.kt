package ar.com.wolox.android.example.ui.example.viewholder

import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.view.NewsView
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime

class NewsViewHolder constructor(private var binding: ItemNewBinding) : RecyclerView.ViewHolder(binding.root),NewsView {

    private val prettyTime : PrettyTime = PrettyTime()

    override fun setDataNews(item : News){
        with(binding) {
            title.text = item.title
            description.text = item.description
            date.text = prettyTime.format(item.date)

            Glide.with(itemView)
                .load(item.image)
                .into(image);
        }
    }
}
package ar.com.wolox.android.example.ui.example.viewholder

import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ItemNewBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.view.NewsView
import ar.com.wolox.android.example.utils.Extras.convertToDate
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime

class NewsViewHolder constructor(private var binding: ItemNewBinding) : RecyclerView.ViewHolder(binding.root),NewsView {

    private val prettyTime : PrettyTime = PrettyTime()

    override fun setDataNews(item : News, userid : Int){
        with(binding) {
            itemView.tag=item
            //Convertir la fecha string a date
            val strdate = item.date.convertToDate()

            title.text = item.commenter
            description.text = item.comment
            date.text = prettyTime.format(strdate?.toDate())

            if(item.likes.contains(userid)) {
                like.setImageResource(R.mipmap.ic_like_on)
            }
            else {
                like.setImageResource(R.mipmap.ic_like_off)
            }

            Glide.with(itemView)
                .load(item.avatar)
                .into(image)
        }
    }

    override fun showError(tipo: String) {}

    override fun showLoading(visibility: Int) {}

    override fun adapterRefresh(){}

    override fun clearRefreshing(){}

    override fun onItemClick(new : News){}
}
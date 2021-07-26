package ar.com.wolox.android.example.ui.example.fragment

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout.*
import android.widget.ImageView
import androidx.core.os.bundleOf
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewdetailBinding
import ar.com.wolox.android.example.BaseConfiguration
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.activity.NewDetailActivity
import ar.com.wolox.android.example.ui.example.presenter.NewDetailPresenter
import ar.com.wolox.android.example.ui.example.view.NewDetailView
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.Extras.convertToDate
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import javax.inject.Inject

class NewDetailFragment private constructor() : WolmoFragment<FragmentNewdetailBinding, NewDetailPresenter>(),
    NewDetailView {

    private val prettyTime : PrettyTime = PrettyTime()
    private lateinit var currentNew : News

    @Inject
    internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_newdetail

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(Extras.Constantes.NEW_DETAIL)

    override fun init() {
        loadData(requireArgument(Extras.Constantes.NEW_DETAIL))
    }

    //Cargar detalles de la noticia
    override fun loadData(new : News){
        with(binding){
            //Guardo el new
            currentNew = new

            //Convertir la fecha string a date
            val strdate = new.date.convertToDate()

            title.text = new.commenter
            description.text = new.comment
            date.text = prettyTime.format(strdate?.toDate())

            //Compruebo si el id del usuario esta en el array del like
            if(new.likes.contains(presenter.getUserId())) {
                like.setImageResource(R.mipmap.ic_like_on_large)
            }

            //Cargar imagen en el tabbar y del fullscreen
            val imageBar = (activity as? NewDetailActivity)?.getImageBar()
            val imagefull = (activity as? NewDetailActivity)?.getImageFull()
            if(imageBar != null && imagefull !=null) {
                Glide.with(requireContext())
                    .load(new.avatar)
                    .into(imageBar)

                Glide.with(requireContext())
                    .load(new.avatar)
                    .into(imagefull)

                //Si se realiza click sobre la imagen del tabbar, se visualiza el fullscreen
                imageBar.setOnClickListener(){
                    imagefull.visibility = VISIBLE
                }

                //Si se realiza click sobre el fullscreen, se oculta el fullscreen
                imagefull.setOnClickListener(){
                    imagefull.visibility = GONE
                }
            }
        }
    }

    override fun setListeners() {
        with(binding){
            like.setOnClickListener{
                presenter.onLikeClicked(currentNew.id)
            }
            swipe.setOnRefreshListener {
                presenter.refreshNew(currentNew.id)
            }
        }
    }

    //Para habilitar o desahabilitar la imagen del like
    override fun enabledLike(enabled : Boolean){
        with(binding){
            like.isEnabled = enabled
        }
    }

    //Para cambiar el estado del like
    override fun changeLike() {
        with(binding){
            val userid = presenter.getUserId()

            //Si el array del like contiene el id del usuario, cambio el icono del like al rellenito
            //sino, cambio al vacio
            if(currentNew.likes.contains(userid)) {
                currentNew.likes.remove(userid)
                like.setImageResource(R.mipmap.ic_like_off_large)
            }
            else {
                currentNew.likes.add(userid)
                like.setImageResource(R.mipmap.ic_like_on_large)
            }

            //Para guardar que algo cambio y refrescar las noticias
            context?.getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME,MODE_PRIVATE)?.edit()?.putBoolean(Extras.Constantes.NEW_CHANGE,true)?.apply()
        }
    }

    override fun showLoading(visibility: Int) {
        with(binding) {
            progress.visibility = visibility
        }
    }

    // Mostrar los errores en cada campo
    override fun showError(tipo: String) {
        when (tipo) {
            Extras.Constantes.NO_MORE_NEWS -> toastFactory.show(R.string.news_no_more_news)
            Extras.Constantes.ERROR_USER_PASS -> toastFactory.show(R.string.fragment_login_error_email_pass)
            Extras.Constantes.ERROR_NETWORK -> toastFactory.show(R.string.fragment_login_error_network)
            Extras.Constantes.ERROR_GENERIC -> toastFactory.show(R.string.unknown_error)
        }
    }

    override fun clearRefreshing(){
        with(binding) {
            swipe.isRefreshing = false
        }
    }

    companion object {

        fun newInstance(new: News) = NewDetailFragment().apply {
            arguments = bundleOf(Extras.Constantes.NEW_DETAIL to new)
        }
    }
}

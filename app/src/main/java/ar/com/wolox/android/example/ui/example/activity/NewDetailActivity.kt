package ar.com.wolox.android.example.ui.example.activity

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityNewdetailBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.example.fragment.NewDetailFragment
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import com.bumptech.glide.Glide

class NewDetailActivity : WolmoActivity<ActivityNewdetailBinding>() {

    override fun layout() = R.layout.activity_newdetail

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(Extras.Constantes.NEW_DETAIL)

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, NewDetailFragment.newInstance(requireArgument(Extras.Constantes.NEW_DETAIL)))
    }

    override fun populate() {
        with(binding){
            setSupportActionBar(toolbar)

            //Mostrar button back en el toolbar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    //Obtener imagen del tabbar
    fun getImageBar() : ImageView {
        with(binding) {
            return expandedImage
        }
    }

    //Obtener imagen para el fullscreen
    fun getImageFull() : ImageView {
        with(binding) {
            return imageFullScreen
        }
    }

    companion object {

        // Se llama pasando un objeto News
        fun start(context: Context, new: News) = context.jumpTo(
            NewDetailActivity::class.java,
            Extras.Constantes.NEW_DETAIL to new)
    }
}

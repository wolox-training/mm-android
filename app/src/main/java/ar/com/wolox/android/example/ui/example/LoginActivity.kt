package ar.com.wolox.android.example.ui.example

import android.content.Context
import android.view.View
import androidx.appcompat.widget.Toolbar
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpToClearingTask

class LoginActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, LoginFragment.newInstance())
    }

    override fun populate() {
        findViewById<Toolbar>(R.id.toolbar).visibility = View.GONE
    }

    companion object {

        // Se usa jumpToClearingTask para que se cierre la activity que lo llamó
        fun start(context: Context) = context.jumpToClearingTask(
            LoginActivity::class.java)
    }
}

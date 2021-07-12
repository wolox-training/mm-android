package ar.com.wolox.android.example.ui.example

import androidx.appcompat.widget.Toolbar
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class SignUpActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, SignUpFragment.newInstance())
    }

    override fun populate() {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
    }
}
package ar.com.wolox.android.example.ui.example.activity

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.ui.example.fragment.MainFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class MainActivity : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, MainFragment.newInstance())
    }
}

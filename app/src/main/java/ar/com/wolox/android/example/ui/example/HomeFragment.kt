package ar.com.wolox.android.example.ui.example

import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentHomeBinding
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class HomeFragment private constructor() : WolmoFragment<FragmentHomeBinding, HomePresenter>(), HomeView {

    override fun layout() = R.layout.fragment_home

    @Inject
    internal lateinit var newsFragment: NewsFragment

    @Inject
    internal lateinit var profileFragment: ProfileFragment

    override fun init() {
        with(binding) {
            //Crear el adaptador con los fragments
            viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
                addFragments(
                    newsFragment to getString(R.string.title_tab_home_news),
                    profileFragment to getString(R.string.title_tab_home_profile))

            }

            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun setListeners() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                changeIcon(position)
            }
        })
        changeIcon(0)
    }

    //Cambiar el icono segun el tab activado
    fun changeIcon(position : Int){
        with(binding) {
            when(position){
                1 -> {
                    tabLayout.getTabAt(0)?.setIcon(R.mipmap.ic_news_list_off)
                    tabLayout.getTabAt(1)?.setIcon(R.mipmap.ic_profile_on)
                }
                else ->{
                    tabLayout.getTabAt(0)?.setIcon(R.mipmap.ic_news_list_on)
                    tabLayout.getTabAt(1)?.setIcon(R.mipmap.ic_profile_off)
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
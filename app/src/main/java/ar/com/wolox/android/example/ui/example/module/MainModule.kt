package ar.com.wolox.android.example.ui.example.module

import ar.com.wolox.android.example.ui.example.activity.MainActivity
import ar.com.wolox.android.example.ui.example.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment
}

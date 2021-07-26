package ar.com.wolox.android.example.ui.example.module

import ar.com.wolox.android.example.ui.example.activity.NewDetailActivity
import ar.com.wolox.android.example.ui.example.fragment.NewDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewDetailModule {

    @ContributesAndroidInjector
    internal abstract fun newDetailActivity(): NewDetailActivity

    @ContributesAndroidInjector
    internal abstract fun newDetailFragment(): NewDetailFragment
}

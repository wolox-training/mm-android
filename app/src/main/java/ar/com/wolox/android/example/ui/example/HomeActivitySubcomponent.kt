package ar.com.wolox.android.example.ui.example

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface HomeActivitySubcomponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}

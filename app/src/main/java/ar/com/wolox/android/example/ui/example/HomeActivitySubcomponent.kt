package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.ui.example.activity.HomeActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface HomeActivitySubcomponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}

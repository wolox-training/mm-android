package ar.com.wolox.android.example.ui.example

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Module {

    @ContributesAndroidInjector
    internal abstract fun exampleActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun exampleFragment(): LoginFragment
}

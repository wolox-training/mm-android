package ar.com.wolox.android.example.ui.example.module

import ar.com.wolox.android.example.ui.example.activity.LoginActivity
import ar.com.wolox.android.example.ui.example.fragment.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment
}

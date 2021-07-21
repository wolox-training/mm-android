package ar.com.wolox.android.example.ui.example.module

import ar.com.wolox.android.example.ui.example.activity.SignUpActivity
import ar.com.wolox.android.example.ui.example.fragment.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignUpModule {

    @ContributesAndroidInjector
    internal abstract fun signUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun SignUpFragment(): SignUpFragment
}

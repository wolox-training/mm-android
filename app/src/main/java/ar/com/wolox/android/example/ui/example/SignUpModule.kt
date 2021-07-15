package ar.com.wolox.android.example.ui.example

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignUpModule {

    @ContributesAndroidInjector
    internal abstract fun signUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun SignUpFragment(): SignUpFragment
}

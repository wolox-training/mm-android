package ar.com.wolox.android.example.ui.example

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExampleModule {

    @ContributesAndroidInjector
    internal abstract fun exampleActivity(): ExampleActivity

    @ContributesAndroidInjector
    internal abstract fun exampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun signUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    internal abstract fun SignUpFragment(): SignUpFragment
}

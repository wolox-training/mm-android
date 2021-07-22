package ar.com.wolox.android.example

import android.content.SharedPreferences
import ar.com.wolox.android.BuildConfig
import ar.com.wolox.android.example.di.DaggerAppComponent
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.WolmoApplication
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import com.google.gson.FieldNamingPolicy
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

class BootstrapApplication : WolmoApplication() {

    override fun onInit() {
        // Initialize Application stuff here
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    override fun applicationInjector(): AndroidInjector<BootstrapApplication> {
        return DaggerAppComponent.builder().networkingComponent(buildDaggerNetworkingComponent())
                .sharedPreferencesName(BaseConfiguration.SHARED_PREFERENCES_NAME).application(this)
                .create(this)
    }

    private fun buildDaggerNetworkingComponent(): NetworkingComponent {
        //Para agregar los header si existen
        val okHttpinterceptor =Interceptor { chain ->
                    val pref =getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME, MODE_PRIVATE)
                    val builder = chain.request().newBuilder()
                    builder.apply {
                        pref.apply {
                            getString(Extras.UserLogin.ACCESS_TOKEN, "")?.let {
                                header("Access-Token", it)
                            }
                            getString(Extras.UserLogin.UID, "")?.let {
                                header("uid", it)
                            }
                            getString(Extras.UserLogin.CLIENT, "")?.let {
                                header("Client", it)
                            }
                        }
                    }

                    chain.proceed(builder.build())
        }

        val builder = DaggerNetworkingComponent
                .builder()
                .baseUrl(BaseConfiguration.EXAMPLE_CONFIGURATION_KEY)
                .okHttpInterceptors(okHttpinterceptor)
                .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        if (BuildConfig.DEBUG) {
            builder.okHttpInterceptors(
                    buildHttpLoggingInterceptor(Level.BODY), ChuckInterceptor(this),okHttpinterceptor)
        }

        return builder.build()
    }

    /**
     * Returns a [HttpLoggingInterceptor] with the newLevel given by **newLevel**.
     *
     * @param newLevel - Logging newLevel for the interceptor.
     * @return New instance of interceptor
     */
    private fun buildHttpLoggingInterceptor(newLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { this.level = newLevel }
    }
}

package ar.com.wolox.android.example.ui.example.presenter

import android.view.View
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.ui.example.view.NewDetailView
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewDetailPresenter @Inject constructor(private val userSession: UserSession, private val newRepository: NewsRepository) : CoroutineBasePresenter<NewDetailView>() {

    fun onLikeClicked(id : String) = launch {
        view?.apply {
            enabledLike(false)
            showLoading(View.VISIBLE)
        }

        // Llamado al endpoint
        networkRequest(newRepository.setLike(id)) {
            onResponseSuccessful { _, _ ->
                view?.apply {
                    // Ocultar progressbar
                    showLoading(View.GONE)

                    //Cambio el estado del like
                    changeLike()

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
            onResponseFailed { _, code ->
                view?.apply {
                    if (code == 401)
                        showError(Extras.Constantes.ERROR_USER_PASS)
                    else
                        showError(Extras.Constantes.ERROR_GENERIC)

                    // Ocultar progressbar
                    showLoading(View.GONE)

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
            onCallFailure {
                view?.apply {
                    showError(Extras.Constantes.ERROR_NETWORK)
                    // Ocultar progressbar
                    showLoading(View.GONE)

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
        }
    }

    fun refreshNew(id : String) = launch {
        view?.enabledLike(false)

        // Llamado al endpoint
        networkRequest(newRepository.getDataNew(id)) {
            onResponseSuccessful { new, _ ->
                view?.apply {
                    if(new!=null) {
                        //seteo el id porque no viene desde back
                        new.id = id

                        //Cargo nuevamente los datos de la noticia obtenida
                        loadData(new)
                    }

                    //Ocultar progressbar
                    showLoading(View.GONE)

                    //Oculto el pullrefresh
                    clearRefreshing()

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
            onResponseFailed { _, code ->
                // Ocultar progressbar
                view?.apply {
                    if (code == 401)
                        showError(Extras.Constantes.ERROR_USER_PASS)
                    else
                        showError(Extras.Constantes.ERROR_GENERIC)

                    //Ocultar progressbar
                    showLoading(View.GONE)

                    //Oculto el pullrefresh
                    clearRefreshing()

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
            onCallFailure {
                view?.apply {
                    showError(Extras.Constantes.ERROR_NETWORK)

                    //Ocultar progressbar
                    showLoading(View.GONE)

                    //Oculto el pullrefresh
                    clearRefreshing()

                    //Habilito imagen del like
                    enabledLike(true)
                }
            }
        }
    }

    //Para obtener el id del usuario logueado
    fun getUserId()=userSession.id
}
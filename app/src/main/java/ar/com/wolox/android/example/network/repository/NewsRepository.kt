package ar.com.wolox.android.example.network.repository

import ar.com.wolox.android.example.network.services.NewsService
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkRequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(private val retrofitServices: RetrofitServices) {

    private val service: NewsService
            get() = retrofitServices.getService(NewsService::class.java)

    //Obtener las noticias
    suspend fun getNews(page : Int) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.getNews(page) }
    }

    //Cambiar estado de like
    suspend fun setLike(id : String) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.setLike(id) }
    }

    //Obtener datos de 1 noticia
    suspend fun getDataNew(id : String) = withContext(Dispatchers.IO) {
        NetworkRequestHandler.safeApiCall { service.getDataNew(id) }
    }
}
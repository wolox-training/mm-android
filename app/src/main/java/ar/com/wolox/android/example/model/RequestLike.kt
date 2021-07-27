package ar.com.wolox.android.example.model

import javax.inject.Inject
import kotlin.collections.ArrayList

/**
Clase RequestNews para modelar las respuestas al dar like a una noticia
 */
data class RequestLike @Inject constructor(
    val message : String,
)
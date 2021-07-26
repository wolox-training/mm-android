package ar.com.wolox.android.example.model

import javax.inject.Inject
import kotlin.collections.ArrayList

/**
Clase RequestUser para modelar la respuesta del login
 */
data class RequestUser @Inject constructor(
    val data : User,
)
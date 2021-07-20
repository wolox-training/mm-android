package ar.com.wolox.android.example.model

import java.util.*
import javax.inject.Inject

/**
Clase News para modelar una noticia
 */
data class News @Inject constructor(
    val id : String,
    val title : String,
    val description : String,
    val date : Date,
    val image : String
)
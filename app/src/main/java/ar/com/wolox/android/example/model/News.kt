package ar.com.wolox.android.example.model

import javax.inject.Inject
import kotlin.collections.ArrayList

/**
Clase News para modelar una noticia
 */
data class News @Inject constructor(
    val id : String,
    val commenter : String,
    val comment : String,
    val date : String,
    val avatar : String,
    val likes : ArrayList<String>,
    val created_at : String,
    val updated_at : String
)
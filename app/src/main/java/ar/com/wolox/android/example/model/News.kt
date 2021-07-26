package ar.com.wolox.android.example.model

import java.io.Serializable
import javax.inject.Inject

/**
Clase News para modelar una noticia
 */
data class News @Inject constructor(
    var id: String,
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val likes: MutableList<Int>,
    val created_at: String,
    val updated_at: String
) : Serializable
package ar.com.wolox.android.example.model

import javax.inject.Inject
import kotlin.collections.ArrayList

/**
Clase RequestNews para modelar las respuestas de noticias al endpoint
 */
data class RequestNews @Inject constructor(
    val page : ArrayList<News>,
    val count: Int,
    val total_pages: Int,
    val total_count: Int,
    val current_page: Int,
    val previous_page: Int,
    val next_page: Int,
    val next_page_url : String,
    val previous_page_url : String
)
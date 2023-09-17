package br.com.fiap.leiapramim.model

import java.time.LocalDateTime

data class ReadImage(
    val imagePath: String?,
    val audioPath: String?,
    val date: LocalDateTime
)

package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty

data class RespostaForm (
    @field:NotEmpty
    val mensagem: String,
    val idAutor: Long
)
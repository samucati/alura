package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class NovoTopicoForm(
    @field:NotEmpty(message = "Título não pode ser vazio")
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    @field:NotNull
    val idCurso: Long,

    @field:NotNull
    val idAutor: Long
)

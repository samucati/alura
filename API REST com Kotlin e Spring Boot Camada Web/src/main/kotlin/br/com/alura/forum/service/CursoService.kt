package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(var cursos: List<Curso>) {
    init {
        val curso = Curso(
            id = 1,
            nome = "abner",
            categoria = "teste"
        )

        cursos = listOf(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cursos.stream().filter {
            it.id == id
        }.findFirst().get()
    }
}

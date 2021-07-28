package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init {
        val curso = Usuario(
            id = 1,
            nome = "abner",
            email = "jose@jose.com"
        )

        usuarios = listOf(curso)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter {
            it.id == id
        }.findFirst().get()
    }
}

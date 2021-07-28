package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico>,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico não encontrado"
    ) {

    init {
        val topico = Topico(
            id = 1,
            titulo = "Teste",
            mensagem = "mensagem teste",
            curso = Curso(
                id = 1,
                nome = "kotlin",
                categoria = "programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "samuel",
                email = "samuel@samuel.com"
            )
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Teste 2",
            mensagem = "mensagem teste 2",
            curso = Curso(
                id = 1,
                nome = "kotlin",
                categoria = "programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "samuel",
                email = "samuel@samuel.com"
            )
        )

        topicos = Arrays.asList(topico,topico2)
    }

    fun listar(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t -> t.id == id }
            .findFirst()
            .orElseThrow{ NotFoundException(notFoundMessage) }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(topicoForm: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(topicoForm)
        topico.id = topicos.size.toLong() +1
        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(topicoForm: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream()
            .filter { t -> t.id == topicoForm.id }
            .findFirst()
            .orElseThrow{ NotFoundException(notFoundMessage) }
        val topicoAtualizado = Topico(
            id= topicoForm.id,
            titulo = topicoForm.titulo,
            mensagem = topicoForm.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )

        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t -> t.id == id }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }

        topicos = topicos.minus(topico)
    }

}
package br.com.alura.listavip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

    @Autowired
    private ConvidadoRepository repository;


    public Iterable<Convidado> obterTodos(){
        Iterable<Convidado> convidados = repository.findAll();
        return convidados;
    }
    
    public Convidado BuscaPeloId(Long idConvidado) {
    	Convidado convidado = repository.findOne(idConvidado);
		return convidado;
    }

    public void salvar(Convidado convidado){
        repository.save(convidado);
    }
    
    public void excluir(Convidado convidado) {
    	repository.delete(convidado);
    }
    
    public List<Convidado> obterConvidadoPor(String nome){
		return repository.findByNome(nome);
	}
}

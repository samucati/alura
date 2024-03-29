package br.com.alura.listavip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.enviadoremail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model){

	    Iterable<Convidado> convidados = service.obterTodos();
	    model.addAttribute("convidados", convidados);

	    return "listaconvidados";
	}
	
	@RequestMapping(value= "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
	                   @RequestParam("telefone") String telefone, Model model ){

	    Convidado novoConvidado = new Convidado(nome, email, telefone);
	    service.salvar(novoConvidado);
	    
	    new EmailService().enviar(nome, email);

	    Iterable<Convidado> convidados = service.obterTodos();
	    model.addAttribute("convidados", convidados);

	    return "redirect:listaconvidados";
	}
	
	@RequestMapping(value= "deletar", method = RequestMethod.GET)
	public String deletar(@RequestParam("idConvidado") Long idConvidado, Model model ){

		Convidado convidado = service.BuscaPeloId(idConvidado);
		service.excluir(convidado);

	    Iterable<Convidado> convidados = service.obterTodos();
	    model.addAttribute("convidados", convidados);

	    return "redirect:listaconvidados";
	}
	
	@RequestMapping(value= "pesquisar", method = RequestMethod.POST)
	public String deletar(@RequestParam("nome") String nome, Model model ){

		List<Convidado> convidados = obterConvidadoPor(nome);
	    model.addAttribute("convidados", convidados);

	    return "redirect:listaconvidados";
	}
	
	public List<Convidado> obterConvidadoPor(String nome){
		return service.obterConvidadoPor(nome);
	}
	
}
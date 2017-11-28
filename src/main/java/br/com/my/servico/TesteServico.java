package br.com.my.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.my.entidades.Teste;
import br.com.my.seguranca.UsuarioLogado;
import br.com.my.seguranca.UsuarioLogadoDetalhes;

@RestController
@RequestMapping(value = "/api/contracts")
public class TesteServico {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Teste> getContracts(@UsuarioLogado UsuarioLogadoDetalhes userDetails) {
		Teste a = new Teste("a - called by user: " + createUserAppendix(userDetails));
		Teste b = new Teste("b - called by user: " + createUserAppendix(userDetails));
		
		List<Teste> contracts = new ArrayList<>();
		contracts.add(a);
		contracts.add(b);
		
		return contracts;
	}
	
	private String createUserAppendix(UsuarioLogadoDetalhes userDetails) {
		return userDetails.getFullName() + " (id= " + userDetails.getId() + ")";
	}
	
}

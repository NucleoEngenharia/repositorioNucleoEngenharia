package com.nucleo.seguranca.bo;

import com.nucleo.seguranca.jdbc.AutenticacaoDAO;
import com.nucleo.seguranca.to.FuncionarioTO;

public class AutenticacaoBO {

	private AutenticacaoDAO autenticacao;
	
	public AutenticacaoBO(){
		autenticacao = new AutenticacaoDAO();
	}
	
	public int autenticarUsuario(String usuario, String senha) {
		return autenticacao.autenticarUsuario(usuario, senha);
	} 
	
	public FuncionarioTO getFuncionarioAutenticado(int idFuncionario){
		return autenticacao.getFuncionarioAutenticado(idFuncionario);
	}
}

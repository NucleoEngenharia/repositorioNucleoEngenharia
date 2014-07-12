package com.nucleo.seguranca.test;

import org.junit.Assert;
import org.junit.Test;

import com.nucleo.seguranca.bo.AutenticacaoBO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;
import com.nucleo.seguranca.to.PerfilTO;


public class AutenticacaoTeste {

	private AutenticacaoBO autenticacao;
	
	public AutenticacaoTeste(){
		this.autenticacao = new AutenticacaoBO();
	}
	
	@Test
	public void testarAutenticacao(){
		String usuario = "alysson.santos";
		String senha = "270690";
		
		int idFuncionario = autenticacao.autenticarUsuario(usuario, senha);
		Assert.assertNotEquals(idFuncionario, 0);
	}
 	
	@Test
	public void testerRetornoUsuarioAutenticado(){
		String usuario = "alysson.santos";
		String senha = "270690";
		FuncionarioTO funcionario = null;
		
		int idFuncionario = autenticacao.autenticarUsuario(usuario, senha);
		
		if(idFuncionario != 0){
			funcionario = autenticacao.getFuncionarioAutenticado(idFuncionario);
		}
		
		Assert.assertNotNull(funcionario);
	}
	
	@Test
	public void testarPerfilFuncionario(){
		String usuario = "alysson.santos";
		String senha = "270690";
		FuncionarioTO funcionario = null;
		
		int idFuncionario = autenticacao.autenticarUsuario(usuario, senha);
		
		if(idFuncionario != 0){
			funcionario = autenticacao.getFuncionarioAutenticado(idFuncionario);
		
			for (PerfilTO perfil : funcionario.getPerfis()) {
				Assert.assertNotNull(perfil);
			}
		}
	}
	
	@Test
	public void testarMenuPerfilFuncionario(){
		String usuario = "alysson.santos";
		String senha = "270690";
		FuncionarioTO funcionario = null;
		
		int idFuncionario = autenticacao.autenticarUsuario(usuario, senha);
		
		if(idFuncionario != 0){
			funcionario = autenticacao.getFuncionarioAutenticado(idFuncionario);
			
			for (PerfilTO perfil : funcionario.getPerfis()) {
				for (MenuTO menu : perfil.getMenus()) {
					Assert.assertNotNull(menu);
				}
			}
		}
	}
}

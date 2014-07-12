package com.nucleo.dao;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;

@Remote
public interface AcessosUsuarioDAO extends DAO<AcessosUsuario, Integer> {
	AcessosUsuario buscarAcessoDoUsuario(int pessoa_id);
	void alterar(AcessosUsuario acessosUsuario, int pessoa_id);
	void inserir(AcessosUsuario acessosUsuario, int pessoa_id);
	AcessosUsuario buscarAcessoDoUsuarioComMenu(int pessoa_id);
}

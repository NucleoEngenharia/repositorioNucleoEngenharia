package com.nucleo.dao.impl;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.dao.AcessosUsuarioDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;

@Stateless
public class AcessosUsuarioDAOImpl extends DAOImpl<AcessosUsuario, Integer> implements AcessosUsuarioDAO {
	@Override
	public AcessosUsuario buscarAcessoDoUsuarioComMenu(int pessoa_id){
		String jpql = "select a from AcessosUsuario a"
				+ " left join fetch a.grupos as g"
				+ " join fetch g.menus"
				+ " where a.id_usuario=:pessoa_id";
		AcessosUsuario acessosUsuario = new AcessosUsuario();
		try{
		acessosUsuario= em.createQuery(jpql, AcessosUsuario.class)
				.setParameter("pessoa_id", pessoa_id)
				.getSingleResult();
		return acessosUsuario;
		}catch(NoResultException e){
			acessosUsuario.setId(0);
			acessosUsuario.setAdministrador(false);
			return acessosUsuario;
		}
	}
	@Override
	public AcessosUsuario buscarAcessoDoUsuario(int pessoa_id) {
		String jpql = "select a from AcessosUsuario a"
				+ " left join fetch a.grupos"
				+ " left join fetch a.projetosAcessiveis"
				+ " where a.id_usuario=:pessoa_id";
		AcessosUsuario acessosUsuario = new AcessosUsuario();
		try{
		acessosUsuario= em.createQuery(jpql, AcessosUsuario.class)
				.setParameter("pessoa_id", pessoa_id)
				.getSingleResult();
		return acessosUsuario;
		}catch(NoResultException e){
			acessosUsuario.setId(0);
			acessosUsuario.setAdministrador(false);
			return acessosUsuario;
		}
	}
	@Override
	public void alterar(AcessosUsuario acessosUsuario, int pessoa_id){
		AcessosUsuario merge = em.merge(acessosUsuario);
		super.alterar(merge, pessoa_id);
	}
	@Override
	public void inserir(AcessosUsuario acessosUsuario, int pessoa_id){
		AcessosUsuario merge = em.merge(acessosUsuario);
		super.inserir(merge, pessoa_id);
	}
	@Override
	public boolean verificaSeUsuarioTemAcesso(int id_usuario){
		boolean existe = false;
		AcessosUsuario a = buscarAcessoDoUsuario(id_usuario);
		if(a.getId()!=0){
			existe = true;
		}
		return existe;
	}
}

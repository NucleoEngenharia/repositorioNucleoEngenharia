package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.dao.GrupoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;

@Stateless
public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO{
	
	@Override
	public List<Grupo>listarGrupos(){
		String jpql = "select distinct g from Grupo g"
				+ " left join fetch g.menus"
				+ " order by g.id";
		return em.createQuery(jpql, Grupo.class)
		.getResultList();
	}
	@Override
	public List<PermissoesMenu>buscarMenusDoGrupo(Grupo grupo){
		String jpql = "select distinct p from PermissoesMenu p"
				+ " left join fetch p.grupo as g"
				+ " where g=:grupo and p.excluido=:excluido";
		return em.createQuery(jpql, PermissoesMenu.class)
		.setParameter("grupo",grupo)
		.setParameter("excluido", false)
		.getResultList();
	}
	public void alterar(Grupo grupo, int pessoa_id){
		Grupo merge = em.merge(grupo);
		super.alterar(merge, pessoa_id);
	}
}

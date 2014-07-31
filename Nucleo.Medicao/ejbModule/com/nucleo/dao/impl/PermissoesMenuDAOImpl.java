package com.nucleo.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.dao.PermissoesMenuDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;
@Stateless
public class PermissoesMenuDAOImpl
extends DAOImpl<PermissoesMenu, Integer>
implements PermissoesMenuDAO{
	@Override
	public PermissoesMenu buscarPermissaoPorIdEDescricao(int id, String descr) {
		String jpql = "select pm from PermissoesMenu pm"
				+ " where pm.grupo =:id and pm.descricao =:descr";
		return em.createQuery(jpql, PermissoesMenu.class)
		.setParameter("id", id)
		.setParameter("descr", descr)
		.getSingleResult();
	}
	@Override
	public List<PermissoesMenu>buscarPermissoesPorGrupo(Grupo grupo){
		String jpql = "select pm PermissoesMenu pm"
				+ " where pm.grupo =:grupoId ";
		return em.createQuery(jpql, PermissoesMenu.class)
				.setParameter("grupoId", grupo.getId())
				.getResultList();
	}
	@Override
	public PermissoesMenu buscarMenu(PermissoesMenu permissoesMenu){
		PermissoesMenu m = new PermissoesMenu();
		String jpql = "select m from PermissoesMenu m"
				+ " where m.id=menuId";
			m = em.createQuery(jpql, PermissoesMenu.class)
			.setParameter("menuId", permissoesMenu.getId())
			.getSingleResult();
			return m;
	}
	@Override
	public void deletarPermissao(PermissoesMenu m,int pessoaId){
		String jpql="update PermissoesMenu p"
				+ " set p.excluido=true,"
				+ " p.usuarioExclusao="+pessoaId+","
				+ " p.dataExclusao='"+Calendar.getInstance().getTime()+"'"
				+ " where p.id="+m.getId()+" or p.idPai="+m.getId()+"";
		em.createQuery(jpql)
		.executeUpdate();
	}
	@Override
	public int buscarUltimoId(){
		String jpql="select p from PermissoesMenu p"
				+ " order by p.id desc";
		return em.createQuery(jpql, PermissoesMenu.class)
		.getResultList().get(0).getId();
	}
		
}

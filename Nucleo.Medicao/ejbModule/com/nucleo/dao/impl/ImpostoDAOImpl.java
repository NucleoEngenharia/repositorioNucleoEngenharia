package com.nucleo.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.nucleo.dao.ImpostoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.entity.cadastro.Projeto;

@Stateless
public class ImpostoDAOImpl extends DAOImpl<Imposto, Integer> implements
		ImpostoDAO {

	@Override
	public List<Imposto> buscarTodosPorProjeto(Projeto projeto) {

		String queryStr = "Select imp from Imposto imp "
				+ "Inner Join projeto_imposto pi on pi.impostos_id = imp.id "
				+ "Where pi.projetos_id = :projeto and imp.excluido = :excluido";

		TypedQuery<Imposto> query = em.createQuery(queryStr, Imposto.class);
		query.setParameter("projeto", projeto.getId());
		query.setParameter("excluido", false);
		return query.getResultList();
	}

	@Override
	public void sincronizarImpostoSAP(List<Imposto> impostosSAP, int usuario) {
		for (Imposto impostoSAP : impostosSAP) {
			boolean existeBase = false;
			for (Imposto imposto : this.buscarTodos()) {
				if (impostoSAP.getIdentificacaoSAP().equals(
						imposto.getIdentificacaoSAP())) {
					existeBase = true;
					// verifica se existe algum valor diferente
					if(!imposto.getDescricao().equals(impostoSAP.getDescricao()) ||
							!imposto.getInativo().equals(impostoSAP.getInativo()) || 
							!imposto.getTaxa().equals(impostoSAP.getTaxa())){
						imposto.setDescricao(impostoSAP.getDescricao());
						imposto.setInativo(impostoSAP.getInativo());
						imposto.setTaxa(impostoSAP.getTaxa());
						
						this.alterar(imposto, usuario);						
					}
				}
			}
			if(!existeBase){
				this.inserir(impostoSAP, usuario);
			}
		}
	}
}

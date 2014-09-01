package com.nucleo.contratos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.contratos.dao.DetalhamentoAtividadeDAO;
import com.nucleo.contratos.entity.Atividades;
import com.nucleo.contratos.entity.DetalhamentoAtividade;
import com.nucleo.contratos.factorBD.Factor;

@Stateless
public class DetalhamentoAtividadeDAOImpl extends Factor implements DetalhamentoAtividadeDAO{
	@Override
	public List<DetalhamentoAtividade> buscarPorAtividade(Atividades atividade) {
		List<DetalhamentoAtividade> d = new ArrayList<DetalhamentoAtividade>();
		String jpql = "select d from DetalhamentoAtividade d"
				+ " left join fetch d.atividades a"
				+ " where a.id=:ativId and a.excluido=:excluido "
				+ " order by d.id";
			d=em.createQuery(jpql, DetalhamentoAtividade.class)
			.setParameter("ativId", atividade.getId())
			.setParameter("excluido",false)
			.getResultList();
		return d;
	}

}

package com.nucleo.contratos.DAOImpl;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.nucleo.contratos.dao.ControleHorasDAO;
import com.nucleo.contratos.entity.ControleDeHorarios;
import com.nucleo.contratos.entity.Funcionario;
import com.nucleo.contratos.factorBD.Factor;

@Stateless
public class ControleHorasDAOImpl extends Factor  implements ControleHorasDAO {

	@Override
	public void registarInicioAtividades(ControleDeHorarios controleDeHorarios) {
			em.persist(controleDeHorarios);
	}

	@Override
	public void registarPonto(ControleDeHorarios controleDeHorarios) {
		em.merge(controleDeHorarios);
	}

	@Override
	public ControleDeHorarios buscarHorariosPorData(Calendar data, Funcionario funcionario) {
		String jpql = "select c from ControleDeHorarios c"
				+ " left join fetch c.funcionario f"
				+ " where f=:funcionario"
				+ " and c.data=:data";
		try{
		 return em.createQuery(jpql, ControleDeHorarios.class)
		 .setParameter("funcionario", funcionario)
		 .setParameter("data", data)
		 .getSingleResult();
		}catch(NoResultException e){
			ControleDeHorarios c = new ControleDeHorarios();
			c.setId(0);
			return c;
		}
	}
}

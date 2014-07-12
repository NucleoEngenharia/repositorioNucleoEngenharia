package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.medicao.RelatoriosRMSGerados;

@Remote
public interface RelatoriosRMSGeradosDAO extends DAO<RelatoriosRMSGerados, Integer> {
	List<RelatoriosRMSGerados>listarTodos();
	RelatoriosRMSGerados buscarPorNome(RelatoriosRMSGerados relatorio);
}

package com.nucleo.dao.impl;

import javax.ejb.Stateless;

import com.nucleo.dao.LogAlteracaoDAO;
import com.nucleo.dao.generic.DAOImpl;
import com.nucleo.entity.log.LogAlteracao;

@Stateless
public class LogAlteracaoDAOImpl extends DAOImpl<LogAlteracao, Integer> implements
		LogAlteracaoDAO {


}

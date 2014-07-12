package com.nucleo.seguranca.bo;

import com.nucleo.seguranca.jdbc.DepartamentoDAO;
import com.nucleo.seguranca.jdbc.FuncionarioDAO;
import com.nucleo.seguranca.jdbc.UnidadeDAO;
import com.nucleo.seguranca.to.DepartamentoTO;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.UnidadeTO;

public class ResponsavelBO{

	private UnidadeDAO unidadeDAO;
	private DepartamentoDAO departDAO;
	private FuncionarioDAO funcDAO;
	
	public ResponsavelBO(){
		unidadeDAO = new UnidadeDAO();
		departDAO = new DepartamentoDAO();
		funcDAO = new FuncionarioDAO();
	}
	
	public UnidadeTO[] getTodasUnidades(){
		return unidadeDAO.getTodasUnidades();
	}
	
	public DepartamentoTO[] getTodosDepartamentos(){
		return departDAO.getTodosDepartamentos();
	}
	
	public FuncionarioTO[] getFuncionarios(int unidade, int departamento){
		return funcDAO.getFuncionariosPorUnidadeDepartamento(unidade, departamento);
	}
	
	public FuncionarioTO[] getFuncionariosPorUnidade(int unidade){
		return funcDAO.getFuncionariosPorUnidade(unidade);
	}
	
}

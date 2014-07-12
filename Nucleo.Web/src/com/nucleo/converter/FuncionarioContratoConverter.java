package com.nucleo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.nucleo.commom.Commom;
import com.nucleo.dao.FuncionarioContratoDAO;
import com.nucleo.entity.medicao.FuncionarioContrato;

@FacesConverter(value = "FuncionarioContratoConverter")
public class FuncionarioContratoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		FuncionarioContrato func = null;
		FuncionarioContratoDAO funcContratoDAO;
		if (id.isEmpty() || id.equals("Selecione o funcionário...")) {
			return func;
		}
		funcContratoDAO = (FuncionarioContratoDAO) Commom.lookup("FuncionarioContratoDAOImpl");
		func = funcContratoDAO.buscarPorID(Integer.parseInt(id));
		return func;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj != null && obj instanceof FuncionarioContrato) {
			FuncionarioContrato func = (FuncionarioContrato) obj;
			return String.valueOf(func.getId());
		}
		return null;
	}

}

package com.nucleo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.nucleo.commom.Commom;
import com.nucleo.dao.PeriodoMedicaoDAO;
import com.nucleo.entity.medicao.PeriodoMedicao;

@FacesConverter(value = "PeriodoMedicaoConverter")
public class PeriodoMedicaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idString) {
		PeriodoMedicao entity = null;
		PeriodoMedicaoDAO dao;
		int id;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			return entity;
		}
		dao = (PeriodoMedicaoDAO) Commom.lookup("PeriodoMedicaoDAOImpl");
		entity = dao.buscarPorID(id);
		return entity;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj != null && obj instanceof PeriodoMedicao) {
			PeriodoMedicao entity = (PeriodoMedicao) obj;
			return String.valueOf(entity.getId());
		}
		return null;
	}

}

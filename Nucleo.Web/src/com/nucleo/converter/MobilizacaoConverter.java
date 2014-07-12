package com.nucleo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.nucleo.commom.Commom;
import com.nucleo.dao.MobilizacaoDAO;
import com.nucleo.entity.medicao.Mobilizacao;

@FacesConverter(value = "MobilizacaoConverter")
public class MobilizacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idString) {
		Mobilizacao entity = null;
		MobilizacaoDAO dao;
		int id;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			return entity;
		}
		dao = (MobilizacaoDAO) Commom.lookup("MobilizacaoDAOImpl");
		entity = dao.buscarPorID(id);
		return entity;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj != null && obj instanceof Mobilizacao) {
			Mobilizacao entity = (Mobilizacao) obj;
			return String.valueOf(entity.getId());
		}
		return null;
	}

}

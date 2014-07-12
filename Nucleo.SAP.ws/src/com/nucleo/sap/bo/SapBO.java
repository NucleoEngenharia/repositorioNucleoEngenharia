package com.nucleo.sap.bo;

import com.nucleo.sap.jdbc.SapDAO;
import com.nucleo.sap.to.ImpostoTO;
import com.nucleo.sap.to.ProjetoTO;

public class SapBO {

	public ProjetoTO[] getProjetos(){
		SapDAO sapDAO = new SapDAO();
		return sapDAO.getProjetos();
	}
	
	public ImpostoTO[] getImpostos(){
		SapDAO sapDAP = new SapDAO();
		return sapDAP.getImpostos();
	}
}

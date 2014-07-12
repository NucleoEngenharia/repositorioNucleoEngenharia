package com.nucleo.projetos.imposto.MB;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.nucleo.commom.Commom;
import com.nucleo.dao.ImpostoDAO;
import com.nucleo.entity.cadastro.Imposto;
import com.nucleo.sap.bo.SapBO;
import com.nucleo.sap.bo.SapBOProxy;
import com.nucleo.sap.to.ImpostoTO;
import com.nucleo.seguranca.to.FuncionarioTO;

@ManagedBean
@RequestScoped
public class ImpostoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ImpostoDAO impostoDAO;
	
	private FuncionarioTO usuarioLogado = Commom.getUsuarioLogado();

	public void sincronizarImpostoSAP(){
		SapBO sapBO = new SapBOProxy();
		List<Imposto> impostos = new ArrayList<Imposto>();
		
		try {
			for (ImpostoTO impTO : sapBO.getImpostos()) {
				Imposto imp = new Imposto();
				imp.setDescricao(impTO.getWtName());
				imp.setIdentificacaoSAP(impTO.getWtCode());
				imp.setTaxa(impTO.getTaxa());
				imp.setInativo(impTO.getInativo());
				
				impostos.add(imp);
			}
			
			impostoDAO.sincronizarImpostoSAP(impostos, usuarioLogado.getPessoa_id());
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sucesso!",
					"Informações sincronizadas com sucesso."));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}

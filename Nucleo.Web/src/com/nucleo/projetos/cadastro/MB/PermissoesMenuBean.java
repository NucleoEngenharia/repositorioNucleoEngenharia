package com.nucleo.projetos.cadastro.MB;

import java.util.Set;



import com.nucleo.commom.Commom;
import com.nucleo.dao.PermissoesMenuDAO;
import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;

public class PermissoesMenuBean {
	
	private PermissoesMenuDAO permissoesMenuDAO = (PermissoesMenuDAO) Commom.lookup("PermissoesMenuDAOImpl");
	
	public boolean apenasLeitura(AcessosUsuario acessosUsuario, String descricao){
		boolean apenasLetirura = false;
		Set<Grupo> grupos = acessosUsuario.getGrupos();
		for(Grupo grupo: grupos){
			Set<PermissoesMenu> menus =  permissoesMenuDAO.buscarPermissoesPorGrupo(grupo);
			for(PermissoesMenu menu: menus){
				if (menu.getDescricao().equals(descricao)&&menu.getAcesso().equals("Leitura")) {
					apenasLetirura = true;
				}
			}
		}
		return apenasLetirura;
	}
}

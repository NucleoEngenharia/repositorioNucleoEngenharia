package com.nucleo.projetos.cadastro.MB;

import java.util.Set;

import com.nucleo.entity.cadastro.controleDeAcessos.AcessosUsuario;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;

public class PermissoesMenuBean {
	
	public boolean apenasLeitura(AcessosUsuario acessosUsuario, String descricao){
		boolean apenasLetirura = false;
		Set<Grupo> grupos = acessosUsuario.getGrupos();
		for(Grupo grupo: grupos){
			Set<PermissoesMenu> menus = grupo.getMenus();
			for(PermissoesMenu menu: menus){
				if (menu.getDescricao().equals(descricao)&&menu.getAcesso().equals("Leitura")) {
					apenasLetirura = true;
				}
			}
		}
		return apenasLetirura;
	}
}

package com.nucleo.dao;

import java.util.List;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.controleDeAcessos.Grupo;
import com.nucleo.entity.cadastro.controleDeAcessos.PermissoesMenu;

@Remote
public interface GrupoDAO extends DAO<Grupo, Integer> {
	List<Grupo>listarGrupos();
	List<PermissoesMenu>buscarMenusDoGrupo(Grupo grupo);
}

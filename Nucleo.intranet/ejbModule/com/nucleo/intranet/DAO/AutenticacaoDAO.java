package com.nucleo.intranet.DAO;

import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;
import com.nucleo.seguranca.to.PerfilTO;

public interface AutenticacaoDAO {
	int autenticarUsuario(String usuario, String senha);
	FuncionarioTO getFuncionarioAutenticado(int idFuncionario);
	PerfilTO[] getPerfisFuncionario(int idFuncionario);
	MenuTO[] getMenusPerfil(int idPerfil);

}

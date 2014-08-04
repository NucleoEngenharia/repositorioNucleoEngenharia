package com.nucleo.intranet.DAO;

import java.util.List;

import com.nucleo.seguranca.to.FuncionarioTO;

public interface FuncionarioDAO {
	List<FuncionarioTO> getFuncionariosPorUnidadeDepartamento(int unidade, int departamento);
	List<FuncionarioTO> getFuncionariosPorUnidade(int unidade);
	List<FuncionarioTO> getUsuariosMedicaoEControle();
}

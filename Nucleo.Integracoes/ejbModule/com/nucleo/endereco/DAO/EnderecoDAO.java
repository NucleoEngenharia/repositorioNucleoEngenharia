package com.nucleo.endereco.DAO;

import java.util.List;

import com.nucleo.endereco.TO.CidadeTO;

public interface EnderecoDAO {
	List<String> buscarCidadePorUF(String uf);
}

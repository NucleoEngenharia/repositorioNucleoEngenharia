package com.nucleo.dao;

import javax.ejb.Remote;

import com.nucleo.dao.generic.DAO;
import com.nucleo.entity.cadastro.Produto;
import com.nucleo.entity.medicao.MedicaoProduto;
import com.nucleo.entity.medicao.PeriodoMedicao;

@Remote
public interface MedicaoProdutoDAO extends DAO<MedicaoProduto, Integer>{

	MedicaoProduto buscarMedicaoPorProdutoPeriodo(PeriodoMedicao periodo, Produto produto);

}

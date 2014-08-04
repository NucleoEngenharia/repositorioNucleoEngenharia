package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.nucleo.FactoringConnection.FactorIntranetImpl;
import com.nucleo.intranet.DAO.UnidadeDAO;
import com.nucleo.seguranca.to.UnidadeTO;
@Stateless
public class UnidadeDAOImpl extends FactorIntranetImpl implements UnidadeDAO {

	private Connection conexao;
	@Override
	public List<UnidadeTO> getTodasUnidades(){
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<UnidadeTO> unidades = new ArrayList<UnidadeTO>();
		
		try {
			conexao = dataSource.getConnection();

			String sql = "SELECT unidade_id, unidade_descricao FROM unidade";
			
			pstmt = conexao.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UnidadeTO unidade = new UnidadeTO();
				unidade.setDescricao(rs.getString("unidade_descricao"));
				unidade.setId(rs.getInt("unidade_id"));
				unidades.add(unidade);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return unidades;
	} 

	
}

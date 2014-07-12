package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nucleo.seguranca.to.DepartamentoTO;

public class DepartamentoDAO {
	
	private Connection conexao;
	
	public DepartamentoTO[] getTodosDepartamentos(){
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<DepartamentoTO> departs = new ArrayList<DepartamentoTO>();
		
		try {
			conexao = DBManager.obterConexao();
			String sql = "SELECT dep_id, dep_descricao FROM departamento Where dep_excluido = '0' ";
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DepartamentoTO depart = new DepartamentoTO();
				depart.setDescricao(rs.getString("dep_descricao"));
				depart.setId(rs.getInt("dep_id"));
				departs.add(depart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return departs.toArray(new DepartamentoTO[departs.size()]);
	} 

}

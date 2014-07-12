package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nucleo.seguranca.to.FuncionarioTO;

public class FuncionarioDAO {
	
	private Connection conexao;
	
	
	public FuncionarioTO[] getFuncionariosPorUnidadeDepartamento(int unidade, int departamento){
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<FuncionarioTO> funcs = new ArrayList<FuncionarioTO>();
		
		try {
			conexao = DBManager.obterConexao();
			String sql = "SELECT * FROM funcionario " +
					"WHERE dep_id = ? AND unidade_id = ? AND func_status = '1' ";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, departamento);
			pstmt.setInt(2, unidade);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FuncionarioTO funcionario = new FuncionarioTO();
				funcionario.setCpf(rs.getString("func_cpf"));
				funcionario.setDt_adm(rs.getDate("func_dt_adm"));
				funcionario.setDt_nasc(rs.getDate("func_dt_nasc"));
				funcionario.setEmail(rs.getString("func_email"));
				funcionario.setNome(rs.getString("func_nome"));
				funcionario.setPessoa_id(rs.getInt("pessoa_id"));
				funcionario.setRamal(rs.getString("func_ramal"));
				funcionario.setStatus(true);
				
				funcs.add(funcionario);
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
		
		return funcs.toArray(new FuncionarioTO[funcs.size()]);
	} 
	
	public FuncionarioTO[] getFuncionariosPorUnidade(int unidade){
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<FuncionarioTO> funcs = new ArrayList<FuncionarioTO>();
		
		try {
			conexao = DBManager.obterConexao();
			String sql = "SELECT * FROM funcionario " +
					"WHERE unidade_id = ? AND func_status = '1' ";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, unidade);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FuncionarioTO funcionario = new FuncionarioTO();
				funcionario.setCpf(rs.getString("func_cpf"));
				funcionario.setDt_adm(rs.getDate("func_dt_adm"));
				funcionario.setDt_nasc(rs.getDate("func_dt_nasc"));
				funcionario.setEmail(rs.getString("func_email"));
				funcionario.setNome(rs.getString("func_nome"));
				funcionario.setPessoa_id(rs.getInt("pessoa_id"));
				funcionario.setRamal(rs.getString("func_ramal"));
				funcionario.setStatus(true);
				
				funcs.add(funcionario);
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
		
		return funcs.toArray(new FuncionarioTO[funcs.size()]);
	} 

}

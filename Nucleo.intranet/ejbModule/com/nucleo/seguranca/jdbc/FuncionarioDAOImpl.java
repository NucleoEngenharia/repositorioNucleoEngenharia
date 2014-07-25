package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.nucleo.FactoringConnection.FactorIntranetImpl;
import com.nucleo.intranet.DAO.FuncionarioDAO;
import com.nucleo.seguranca.to.FuncionarioTO;
@Stateless
public class FuncionarioDAOImpl extends FactorIntranetImpl implements FuncionarioDAO {
	
	private static Connection conexao =null;
	private PreparedStatement pstmt = null;
	private ResultSet   rs = null;
	@PostConstruct
	private void init(){
		try {
			conexao = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private FuncionarioTO populaDados(ResultSet rs,FuncionarioTO funcionario){
		try {
		funcionario.setCpf(rs.getString("func_cpf"));
		funcionario.setDt_adm(rs.getDate("func_dt_adm"));
		funcionario.setDt_nasc(rs.getDate("func_dt_nasc"));
		funcionario.setEmail(rs.getString("func_email"));
		funcionario.setNome(rs.getString("func_nome"));
		funcionario.setPessoa_id(rs.getInt("pessoa_id"));
		funcionario.setRamal(rs.getString("func_ramal"));
		funcionario.setStatus(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}
	@Override
	public List<FuncionarioTO> getFuncionariosPorUnidadeDepartamento(int unidade, int departamento){
		List<FuncionarioTO> funcs = new ArrayList<FuncionarioTO>();
		
		try {
			String sql = "SELECT * FROM funcionario " +
					"WHERE dep_id = ? AND unidade_id = ? AND func_status = '1' ";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, departamento);
			pstmt.setInt(2, unidade);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FuncionarioTO funcionario = new FuncionarioTO();
				populaDados(rs, funcionario);
				funcs.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return funcs;
	} 
	@Override
	public List<FuncionarioTO> getFuncionariosPorUnidade(int unidade){
		List<FuncionarioTO> funcs = new ArrayList<FuncionarioTO>();
		
		try {
			String sql = "SELECT * FROM funcionario " +
					"WHERE unidade_id = ? AND func_status = '1' ";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, unidade);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FuncionarioTO funcionario = new FuncionarioTO();
				populaDados(rs, funcionario);
				funcs.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return funcs;
	}
	@Override
	public List<FuncionarioTO> getUsuariosMedicaoEControle() {
		List<FuncionarioTO>funcionarios = new ArrayList<FuncionarioTO>();
		try {
			String sql = "select f.* from funcionario f" +
					" inner join funcionario_perfil fp" +
					" on(f.pessoa_id=fp.pessoa_id) and fp.perfil_id=11 and func_status = '1' ";
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FuncionarioTO funcionario = new FuncionarioTO();
				funcionario = populaDados(rs, funcionario);
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	} 
}

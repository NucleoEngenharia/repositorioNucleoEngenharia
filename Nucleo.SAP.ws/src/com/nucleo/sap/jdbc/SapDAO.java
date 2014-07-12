package com.nucleo.sap.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nucleo.sap.to.ImpostoTO;
import com.nucleo.sap.to.ProjetoTO;

public class SapDAO {

	private Connection conexao;
	
	public ProjetoTO[] getProjetos() {
		
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<ProjetoTO> projetos = new ArrayList<ProjetoTO>();
		
		try {
			conexao = DBManager.obterConexao();
			String sql = 
					"SELECT U_Proj_Atividade,U_Proj_Setor,PrjCode,PrjName,U_Proj_DtInicio,U_Proj_DtFim,U_Proj_VlrOrigProj,U_Proj_Setor,U_Proj_Atividade " +
					"FROM OPRJ " +
					"WHERE Active = 'Y' ";
			
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProjetoTO proj = new ProjetoTO();
				proj.setCN(rs.getString("PrjCode"));
				proj.setNome(rs.getString("PrjName"));
				proj.setSetor(rs.getInt("U_Proj_Setor"));
				proj.setAtividade(rs.getInt("U_Proj_Atividade"));
				proj.setVlOriginal(rs.getBigDecimal("U_Proj_VlrOrigProj"));
				proj.setDtInicio(rs.getDate("U_Proj_DtInicio"));
				proj.setDtFim(rs.getDate("U_Proj_DtFim"));
				proj.setAtividade(rs.getInt("U_Proj_Atividade"));
				proj.setSetor(rs.getInt("U_Proj_Setor"));
				
				projetos.add(proj);
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

		return projetos.toArray(new ProjetoTO[projetos.size()]);
	}

	public ImpostoTO[] getImpostos() {

		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<ImpostoTO> impostos = new ArrayList<ImpostoTO>();
		
		try {
			conexao = DBManager.obterConexao();
			String sql = 
					"SELECT WTCode,WTName,Rate,Inactive " +
					"FROM OWHT " +
					"where EffecDate <= GETDATE() ";
			
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ImpostoTO imp = new ImpostoTO();
				imp.setWtName(rs.getString("WTName"));
				imp.setWtCode(rs.getString("WTCode"));
				imp.setTaxa(rs.getBigDecimal("Rate"));
				imp.setInativo(rs.getString("Inactive"));
				
				impostos.add(imp);
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

		return impostos.toArray(new ImpostoTO[impostos.size()]);
	}
	
}

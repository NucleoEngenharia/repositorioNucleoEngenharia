package com.nucleo.seguranca.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.nucleo.intranet.DAO.AutenticacaoDAO;
import com.nucleo.intranet.FactoringConnection.FactorImpl;
import com.nucleo.seguranca.to.FuncionarioTO;
import com.nucleo.seguranca.to.MenuTO;
import com.nucleo.seguranca.to.PerfilTO;
@Stateless
public class AutenticacaoDAOImpl extends FactorImpl implements AutenticacaoDAO{
	
	protected static Connection conexao =null;
	@PostConstruct
	public void init(){
		try {
			conexao = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int autenticarUsuario(String usuario, String senha) {
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		int idFuncionario = 0;
		
		try {
			String sql = 
					"SELECT count(1) result, pessoa_id FROM funcionario " +
					"WHERE func_email = ? AND func_senha = MD5(?) AND func_status = '1'" +
					"group by pessoa_id";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, usuario + "@nucleoengenharia.com.br");
			pstmt.setString(2, senha);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				idFuncionario = rs.getInt("pessoa_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return idFuncionario;
	}
	@Override
	public FuncionarioTO getFuncionarioAutenticado(int idFuncionario) {
		
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		FuncionarioTO funcionario = null;
		
		try {
			String sql = "SELECT * FROM funcionario WHERE pessoa_id = ?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idFuncionario);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				funcionario = new FuncionarioTO();
				funcionario.setCpf(rs.getString("func_cpf"));
				funcionario.setDt_adm(rs.getDate("func_dt_adm"));
				funcionario.setDt_nasc(rs.getDate("func_dt_nasc"));
				funcionario.setEmail(rs.getString("func_email"));
				funcionario.setNome(rs.getString("func_nome"));
				funcionario.setPessoa_id(rs.getInt("pessoa_id"));
				funcionario.setRamal(rs.getString("func_ramal"));
				funcionario.setStatus(true);
				
				funcionario.setPerfis(getPerfisFuncionario(funcionario.getPessoa_id()));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return funcionario;
	}
	@Override
	public PerfilTO[] getPerfisFuncionario(int idFuncionario){
		PreparedStatement pstmt = null;
		ResultSet   rs = null;
		List<PerfilTO> perfis = new ArrayList<PerfilTO>();
		
		try {
			String sql = "SELECT perfil_descricao, perfil_comite, p.perfil_id id FROM funcionario_perfil fp "+
					"Inner Join perfil p on fp.perfil_id = p.perfil_id AND perfil_excluido = '0' "+
					"WHERE fp.pessoa_id = ? ";
			
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idFuncionario);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PerfilTO perfil = new PerfilTO();
				perfil.setDescricao(rs.getString("perfil_descricao"));
				perfil.setExcluido(false);
				perfil.setId(rs.getInt("id"));
				
				perfil.setMenus(getMenusPerfil(perfil.getId()));
				
				perfis.add(perfil);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perfis.toArray(new PerfilTO[perfis.size()]);
	} 
	@Override
	public MenuTO[] getMenusPerfil(int idPerfil) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MenuTO> menus = new ArrayList<MenuTO>();
		
		try {
			String sql = "SELECT menu_descricao, m.menu_id idMenu, menu_pai, menu_url FROM perfil_menu pm "+
					"Inner Join menu m on pm.menu_id = m.menu_id AND menu_excluido = '0' "+
					"WHERE perfil_id = ? AND menu_medicao = '1'";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, idPerfil);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MenuTO menu = new MenuTO();
				menu.setDescricao(rs.getString("menu_descricao"));
				menu.setId(rs.getInt("idMenu"));
				menu.setMenuPai(rs.getInt("menu_pai"));
				menu.setUrl(rs.getString("menu_url"));
				menu.setExcluido(false);
				
				menus.add(menu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus.toArray(new MenuTO[menus.size()]);
	}
	
}

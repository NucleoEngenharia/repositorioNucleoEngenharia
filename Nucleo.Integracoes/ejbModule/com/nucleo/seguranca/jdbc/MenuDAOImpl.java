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
import com.nucleo.intranet.DAO.MenuDAO;
import com.nucleo.seguranca.to.MenuTO;

@Stateless
public class MenuDAOImpl extends FactorIntranetImpl implements MenuDAO {

	private static Connection conexao =null;
	private PreparedStatement pstmt = null;
	private ResultSet   rs = null;
	@PostConstruct
	public void init(){
		try {
			conexao = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private MenuTO populaDados(ResultSet rs,MenuTO menuTO){
		try {
			menuTO.setId(rs.getInt("menu_id"));
			menuTO.setDescricao(rs.getString("menu_descricao"));
			menuTO.setUrl(rs.getString("menu_url"));
			menuTO.setMenuPai(rs.getInt("menu_pai"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuTO;
	}

	@Override
	public List<MenuTO> listarMenusFilhos(String sistema,String menuPai) {
		List<MenuTO>menus = new ArrayList<MenuTO>();
		String sql = "select * from menu where "+sistema+"='1' and menu_pai='"+menuPai+"'";
		try {
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MenuTO menuTO = new MenuTO();
				menuTO = populaDados(rs, menuTO);
				menus.add(menuTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
	@Override
	public List<MenuTO>listarTodosMenus(String sistema){
		List<MenuTO>menus = new ArrayList<MenuTO>();
		String sql = "select * from menu where "+sistema+"='1'";
		try {
			pstmt = conexao.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MenuTO menuTO = new MenuTO();
				menuTO = populaDados(rs, menuTO);
				menus.add(menuTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
}

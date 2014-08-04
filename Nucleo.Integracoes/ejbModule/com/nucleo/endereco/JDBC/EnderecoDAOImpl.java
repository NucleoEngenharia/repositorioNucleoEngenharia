package com.nucleo.endereco.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.nucleo.FactoringConnection.FactorEndImpl;
import com.nucleo.endereco.DAO.EnderecoDAO;
import com.nucleo.endereco.TO.CidadeTO;
@Stateless
public class EnderecoDAOImpl extends FactorEndImpl implements EnderecoDAO {
	
	@PostConstruct
	public void init() {
		try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection conn=null;
	private PreparedStatement pmst;
	private ResultSet rs;

	@Override
	public List<String> buscarCidadePorUF(String uf) {
		String sql = "SELECT distinct cidade FROM tend_cidade WHERE uf=? ";
		CidadeTO cidadeTO = new CidadeTO();
		List<String>list=new ArrayList<String>();
		
		try {
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, uf);
			rs = pmst.executeQuery();
			
			while(rs.next()){
			cidadeTO.setNome(rs.getString("cidade"));
			list.add(cidadeTO.getNome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

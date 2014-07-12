package com.nucleo.seguranca.to;

import java.io.Serializable;

public class PerfilTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private boolean excluido;
	
	private MenuTO[] menus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isExcluido() {
		return excluido;
	}
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
	public MenuTO[] getMenus() {
		return menus;
	}
	public void setMenus(MenuTO[] menus) {
		this.menus = menus;
	}

	
}

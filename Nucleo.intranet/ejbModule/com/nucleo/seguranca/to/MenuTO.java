package com.nucleo.seguranca.to;

import java.io.Serializable;

public class MenuTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private int menuPai;
	private boolean excluido;
	private String url;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getMenuPai() {
		return menuPai;
	}
	public void setMenuPai(int menuPai) {
		this.menuPai = menuPai;
	}

	
}

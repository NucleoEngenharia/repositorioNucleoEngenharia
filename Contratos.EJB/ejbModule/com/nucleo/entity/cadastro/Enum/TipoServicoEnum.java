package com.nucleo.entity.cadastro.Enum;

import java.util.HashMap;
import java.util.Map;

public enum TipoServicoEnum {
	PRODUTO(0, "Produto"), EQUIPE(1, "Equipe");
	
	private int valor;
	private String nome;
	
	private static Map<Integer, TipoServicoEnum> relations;
	
	TipoServicoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoServicoEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (TipoServicoEnum e : values())
			map.put(e.getValor(), e.getNome());

		return map;
	}
	
	static {
		relations = new HashMap<Integer, TipoServicoEnum>();
		for (TipoServicoEnum e : values())
			relations.put(e.getValor(), e);
	}
}

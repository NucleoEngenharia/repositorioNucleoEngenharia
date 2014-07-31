package com.nucleo.entity.cadastro.eNum;

import java.util.HashMap;
import java.util.Map;

public enum StatusProjetoEnum {

	ATIVO(0, "Ativo"), ENCERRADO(1, "Encerrado"), PARALISADO(2, "Paralisado"), S_OIS(3,"S/OIS");
	
	private int valor;
	private String nome;
	private static Map<Integer, StatusProjetoEnum> relations;
	
	StatusProjetoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static StatusProjetoEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (StatusProjetoEnum e : values())
			map.put(e.getValor(), e.getNome());
		
		return map;
	}
	
	static {
		relations = new HashMap<Integer, StatusProjetoEnum>();
		for (StatusProjetoEnum e : values())
			relations.put(e.getValor(), e);
	}	
}
